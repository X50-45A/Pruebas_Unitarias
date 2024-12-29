package micromobility;

import exceptions.NotEnoughWalletException;
import org.junit.jupiter.api.Test;

public interface JourneyRealizeHandlerTestInterface {

    /**
     * Test for a successful scanQR operation.
     */
    @Test
    void testScanQR_Successful();

    /**
     * Test for scanQR throwing CorruptedImgException.
     */
    @Test
    void testScanQR_ThrowsCorruptedImgException();

    /**
     * Test for scanQR throwing PMVNotAvailException.
     */
    @Test
    void testScanQR_ThrowsPMVNotAvailException();

    /**
     * Test for a successful startDriving operation.
     */
    @Test
    void testStartDriving_Successful();

    /**
     * Test for startDriving throwing ProceduralException.
     */
    @Test
    void testStartDriving_ThrowsProceduralException();

    /**
     * Test for a successful broadcastStationID operation.
     */
    @Test
    void testBroadcastStationID_Successful();

    /**
     * Test for selecting a payment method and realizing a successful payment.
     */
    @Test
    void testSelectPaymentMethodAndRealizePayment_Successful() throws NotEnoughWalletException;

    /**
     * Test for selecting a payment method and realizing payment throwing NotEnoughWalletException.
     */
    @Test
    void testSelectPaymentMethodAndRealizePayment_ThrowsNotEnoughWalletException();

}
