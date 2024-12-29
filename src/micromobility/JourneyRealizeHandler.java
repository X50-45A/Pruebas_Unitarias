package micromobility;

import data.GeographicPoint;
import data.StationID;
import data.UserAccount;
import data.VehicleID;
import micromobility.payment.Wallet;
import micromobility.payment.WalletPayment;
import services.Server;
import services.smartfeatures.ArduinoMicroController;
import services.smartfeatures.QRDecoder;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import exceptions.*;


public class JourneyRealizeHandler {
   private final JourneyService journeyService;
   private final QRDecoder qrDecoder;
   private final Server server;
   private final ArduinoMicroController arduino;
   private Wallet wallet;
   // The class members
   // The constructor/s
   public JourneyRealizeHandler(JourneyService journeyService, QRDecoder qrDecoder, Server server, ArduinoMicroController arduino) {
      this.journeyService = journeyService;
      this.qrDecoder = qrDecoder;
      this.server = server;
      this.arduino = arduino;
   }
    // Different input events that intervene
    // User interface input events
    public void scanQR(BufferedImage qrImage) throws ConnectException, InvalidPairingArgsException, CorruptedImgException, PMVNotAvailException, ProceduralException, exceptions.ConnectException {
       VehicleID vehicleID = qrDecoder.getVehicleID(qrImage);
       server.checkPMVAvail(vehicleID);
       // Further pairing logic...
    }
   public void unPairVehicle() throws ConnectException, InvalidPairingArgsException, PairingNotFoundException, ProceduralException {
      // Logic for unpairing vehicle...
      journeyService.getEndStation();
   }

   // Input events from Bluetooth
   public void broadcastStationID(StationID stationID) throws ConnectException {
      // Logic for broadcasting station ID...
   }

   // Input events from Arduino microcontroller
   public void startDriving() throws ConnectException, ProceduralException, exceptions.ConnectException {
      arduino.startDriving();
      // Further initialization logic...
   }

   public void stopDriving() throws ConnectException, ProceduralException, exceptions.ConnectException {
      arduino.stopDriving();
      // Finalization logic...
   }

   // Internal operations
   private float calculateDistance(GeographicPoint startLocation, GeographicPoint endLocation) {
      double latDiff = Math.toRadians(endLocation.getLatitude() - startLocation.getLatitude());
      double lonDiff = Math.toRadians(endLocation.getLongitude() - startLocation.getLongitude());
      double startLatRad = Math.toRadians(startLocation.getLatitude());
      double endLatRad = Math.toRadians(endLocation.getLatitude());

      double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
              Math.cos(startLatRad) * Math.cos(endLatRad) *
                      Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);
      double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
      double earthRadius = 6371; // Radius of the earth in km

      return (float) (earthRadius * c); // Distance in km
   }

   private void calculateValues(GeographicPoint endLocation, LocalDateTime endTime, GeographicPoint startLocation, LocalDateTime startTime) {
      float distance = calculateDistance(startLocation, endLocation);
      long durationInSeconds = Duration.between(startTime, endTime).getSeconds();
      float durationInHours = durationInSeconds / 3600f;
      float averageSpeed = (durationInHours > 0) ? distance / durationInHours : 0;

      System.out.printf("Calculated Values: Distance=%.2f km, Duration=%d seconds, AvgSpeed=%.2f km/h\n",
              distance, durationInSeconds, averageSpeed);
   }

   private BigDecimal calculateImport(float distance, int duration, float averageSpeed) {
      BigDecimal baseRate = new BigDecimal("0.50"); // Base rate per km
      BigDecimal speedMultiplier = (averageSpeed > 25) ? new BigDecimal("1.20") : BigDecimal.ONE; // Speed premium
      BigDecimal timeMultiplier = (duration > 3600) ? new BigDecimal("1.15") : BigDecimal.ONE; // Long trip premium

      BigDecimal cost = baseRate.multiply(BigDecimal.valueOf(distance))
              .multiply(speedMultiplier)
              .multiply(timeMultiplier);

      System.out.printf("Calculated Cost: %.2f\n", cost);
      return cost;
   }
   public void realizePayment(UserAccount user, BigDecimal amount, char payMethod,Wallet wallet) throws ConnectException, NotEnoughWalletException {
      if (payMethod == 'W') { // Pago con monedero
         WalletPayment payment = new WalletPayment(user, amount, wallet);
         payment.processPayment();
         server.registerPayment(journeyService,user, amount,payMethod);
      } else {
         throw new IllegalArgumentException("Invalid payment method");
      }
   }
  // Setter methods for injecting dependences
}

