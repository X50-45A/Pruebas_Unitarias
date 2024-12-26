package micromobility;

import data.GeographicPoint;
import data.StationID;
import data.VehicleID;
import services.Server;
import services.smartfeatures.ArduinoMicroController;
import services.smartfeatures.QRDecoder;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class JourneyRealizeHandler {
   private final JourneyService journeyService;
   private final QRDecoder qrDecoder;
   private final Server server;
   private final ArduinoMicroController arduino;
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
    public void scanQR(BufferedImage qrImage) throws ConnectException, InvalidPairingArgsException, CorruptedImgException, PMVNotAvailException, ProceduralException {
       PMVehicle vehicleID = qrDecoder.getVehicleID(qrImage);
       server.checkPMVAvail(vehicleID);
       // Further pairing logic...
    }
   public void unPairVehicle() throws ConnectException, InvalidPairingArgsException, PairingNotFoundException, ProceduralException {
      // Logic for unpairing vehicle...
   }

   // Input events from Bluetooth
   public void broadcastStationID(StationID stationID) throws ConnectException {
      // Logic for broadcasting station ID...
   }

   // Input events from Arduino microcontroller
   public void startDriving() throws ConnectException, ProceduralException {
      arduino.startDriving();
      // Further initialization logic...
   }

   public void stopDriving() throws ConnectException, ProceduralException {
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
 (. . .) // Setter methods for injecting dependences
}

