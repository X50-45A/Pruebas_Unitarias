package micromobility;
import data.*;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Server;
import services.smartfeatures.ArduinoMicroController;
import services.smartfeatures.QRDecoder;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JourneyRealizeHandlerTest {

    private JourneyRealizeHandler journeyRealizeHandler;
    private JourneyService journeyService;
    private QRDecoder qrDecoder;
    private Server server;
    private ArduinoMicroController arduino;

    @BeforeEach
    void setUp() {
        journeyService = new JourneyService(
                new UserAccount("testUser"),
                new VehicleID("vehicle123"),
                new StationID("station123"),
                new GeographicPoint(40.7128f, -74.0060f)
        );
        qrDecoder = new QRDecoder() {
            @Override
            public VehicleID getVehicleID(BufferedImage qrImg) throws CorruptedImgException {
                if (qrImg == null) {
                    throw new CorruptedImgException("QR image is null");
                }
                return new VehicleID("vehicle123");
            }
        };
        server = new Server() {
            @Override
            public void checkPMVAvail(VehicleID vhID) throws PMVNotAvailException, ConnectException {
                if (!vhID.getValue().equals("vehicle123")) {
                    throw new PMVNotAvailException("Vehicle not available");
                }
            }

            @Override
            public void registerPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date) throws InvalidPairingArgsException, ConnectException {
                if (user == null || veh == null || st == null || loc == null || date == null) {
                    throw new InvalidPairingArgsException("Invalid arguments for pairing");
                }
            }

            @Override
            public void stopPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date, float avSp, float dist, int dur, BigDecimal imp) throws InvalidPairingArgsException, ConnectException {
                if (user == null || veh == null || st == null || loc == null || date == null) {
                    throw new InvalidPairingArgsException("Invalid arguments for stopping pairing");
                }
            }

            @Override
            public void setPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date) {
                // Simulated server behavior
            }

            @Override
            public void unPairRegisterService(JourneyService s) throws PairingNotFoundException {
                if (s == null) {
                    throw new PairingNotFoundException("Pairing not found");
                }
            }

            @Override
            public void registerLocation(VehicleID veh, StationID st) {
                // Simulated server behavior
            }
        };

        arduino = new ArduinoMicroController() {
            @Override
            public void setBTconnection() throws ConnectException {
                // Simulated BT connection
            }

            @Override
            public void startDriving() throws PMVPhisicalException, ConnectException, ProceduralException {
                // Simulated driving start
            }

            @Override
            public void stopDriving() throws PMVPhisicalException, ConnectException, ProceduralException {
                // Simulated driving stop
            }

            @Override
            public void undoBTconnection() {
                // Simulated undo BT connection
            }
        };

        journeyRealizeHandler = new JourneyRealizeHandler(journeyService, qrDecoder, server, arduino);
    }

    @Test
    void testScanQR_Successful() {
        BufferedImage mockQRImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        assertDoesNotThrow(() -> journeyRealizeHandler.scanQR(mockQRImage));
    }

    @Test
    void testScanQR_ThrowsCorruptedImgException() {
        assertThrows(CorruptedImgException.class, () -> journeyRealizeHandler.scanQR(null));
    }

    @Test
    void testScanQR_ThrowsPMVNotAvailException() {
        BufferedImage mockQRImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        assertThrows(PMVNotAvailException.class, () -> {
            qrDecoder.getVehicleID(mockQRImage); // Decodes QR
            server.checkPMVAvail(new VehicleID("invalidVehicle"));
        });
    }

    @Test
    void testStartDriving_Successful() {
        assertDoesNotThrow(() -> journeyRealizeHandler.startDriving());
    }

    @Test
    void testStartDriving_ThrowsProceduralException() {
        ArduinoMicroController faultyArduino = new ArduinoMicroController() {
            @Override
            public void startDriving() throws ProceduralException {
                throw new ProceduralException("Error starting driving");
            }

            @Override
            public void setBTconnection() throws ConnectException {}

            @Override
            public void stopDriving() throws PMVPhisicalException, ConnectException, ProceduralException {}

            @Override
            public void undoBTconnection() {}
        };

        journeyRealizeHandler = new JourneyRealizeHandler(journeyService, qrDecoder, server, faultyArduino);

        assertThrows(ProceduralException.class, () -> journeyRealizeHandler.startDriving());
    }

    @Test
    void testBroadcastStationID_Successful() {
        StationID stationID = new StationID("station123");

        assertDoesNotThrow(() -> journeyRealizeHandler.broadcastStationID(stationID));
    }
}

