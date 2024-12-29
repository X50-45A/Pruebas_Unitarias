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
   private void calculateValues(GeographicPoint endLocation, LocalDateTime endTime) {
      // Logic to calculate distance, duration, and average speed...
   }

   private void calculateImport(float distance, int duration, float averageSpeed, LocalDateTime endTime) {
      // Logic to calculate the cost of the journey...
   }
   public void realizePayment(UserAccount user, BigDecimal amount, char payMethod) throws ConnectException, NotEnoughWalletException {
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

