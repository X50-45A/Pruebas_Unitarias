package micromobility;

import data.*;
import exceptions.*;
import micromobility.payment.Wallet;
import micromobility.payment.WalletPayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Server;
import services.smartfeatures.ArduinoMicroController;
import services.smartfeatures.QRDecoder;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JourneyRealizeHandlerTest implements JourneyRealizeHandlerTestInterface{

    private JourneyRealizeHandler journeyRealizeHandler;
    private JourneyService journeyService;
    private QRDecoder qrDecoder;
    private Server server;
    private ArduinoMicroController arduino;
    private Wallet wallet;

    @BeforeEach
    void setUp() {
        journeyService = new JourneyService(
                new UserAccount("testUser@example.com"),
                new VehicleID("PMV12345"),
                new StationID("ST1234"),
                new GeographicPoint(40.7128f, -74.0060f)
        );

        qrDecoder = new QRDecoder() {
            @Override
            public VehicleID getVehicleID(BufferedImage qrImg) throws CorruptedImgException {
                if (qrImg == null) {
                    throw new CorruptedImgException("QR image is null");
                }
                return new VehicleID("PMV12345");
            }
        };

        server = new Server() {
            @Override
            public void checkPMVAvail(VehicleID vhID) throws PMVNotAvailException, ConnectException {
                if (!vhID.getValue().equals("PMV12345")) {
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

            @Override
            public void registerPayment(JourneyService journeyService, UserAccount user, BigDecimal imp, char payMeth) throws ConnectException {

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

        wallet = new Wallet(new BigDecimal("100.00"));

        journeyRealizeHandler = new JourneyRealizeHandler(journeyService, qrDecoder, server, arduino);
    }

    @Test
    public void testScanQR_Successful() {
        BufferedImage mockQRImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        assertDoesNotThrow(() -> journeyRealizeHandler.scanQR(mockQRImage));
    }

    @Test
    public void testScanQR_ThrowsCorruptedImgException() {
        assertThrows(CorruptedImgException.class, () -> journeyRealizeHandler.scanQR(null));
    }

    @Test
    public void testScanQR_ThrowsPMVNotAvailException() {
        BufferedImage mockQRImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        // Crear un server que siempre lance PMVNotAvailException
        Server testServer = new Server() {
            @Override
            public void checkPMVAvail(VehicleID vhID) throws PMVNotAvailException {
                throw new PMVNotAvailException("Vehicle not available");
            }

            @Override
            public void registerPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date) {}

            @Override
            public void stopPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date, float avSp, float dist, int dur, BigDecimal imp) {}

            @Override
            public void setPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date) {}

            @Override
            public void unPairRegisterService(JourneyService s) {}

            @Override
            public void registerLocation(VehicleID veh, StationID st) {}

            @Override
            public void registerPayment(JourneyService journeyService, UserAccount user, BigDecimal imp, char payMeth) throws ConnectException {

            }
        };

        // Crear una nueva instancia del handler con el server modificado
        JourneyRealizeHandler handler = new JourneyRealizeHandler(journeyService, qrDecoder, testServer, arduino);

        // Probar que se lanza la excepción
        assertThrows(PMVNotAvailException.class, () -> handler.scanQR(mockQRImage));
    }

    @Test
    public void testStartDriving_Successful() {
        assertDoesNotThrow(() -> journeyRealizeHandler.startDriving());
    }

    @Test
    public void testStartDriving_ThrowsProceduralException() {
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
    public void testBroadcastStationID_Successful() {
        StationID stationID = new StationID("ST1234");
        assertDoesNotThrow(() -> journeyRealizeHandler.broadcastStationID(stationID));
    }

    @Test
    public void testSelectPaymentMethodAndRealizePayment_Successful() throws NotEnoughWalletException {
        WalletPayment payment = new WalletPayment(journeyService.getUser(), new BigDecimal("30.00"), wallet);
        payment.processPayment();
        assertEquals(new BigDecimal("70.00"), wallet.getBalance());
    }

    @Test
    public void testSelectPaymentMethodAndRealizePayment_ThrowsNotEnoughWalletException() {
        WalletPayment payment = new WalletPayment(journeyService.getUser(), new BigDecimal("150.00"), wallet);
        assertThrows(NotEnoughWalletException.class, payment::processPayment);
        assertEquals(new BigDecimal("100.00"), wallet.getBalance());
    }
}