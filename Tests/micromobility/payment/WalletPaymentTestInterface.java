package micromobility.payment;

import exceptions.NotEnoughWalletException;
import org.junit.jupiter.api.Test;

public interface WalletPaymentTestInterface {

    /**
     * Test for creating a valid WalletPayment instance.
     */
    @Test
    void testWalletPaymentCreation();

    /**
     * Test for WalletPayment creation with a null service ID.
     */
    @Test
    void testWalletPaymentWithNullServiceID();

    /**
     * Test for WalletPayment creation with an empty service ID.
     */
    @Test
    void testWalletPaymentWithEmptyServiceID();

    /**
     * Test for WalletPayment creation with a null amount.
     */
    @Test
    void testWalletPaymentWithNullAmount();

    /**
     * Test for WalletPayment creation with a zero amount.
     */
    @Test
    void testWalletPaymentWithZeroAmount();

    /**
     * Test for WalletPayment creation with a negative amount.
     */
    @Test
    void testWalletPaymentWithNegativeAmount();

    /**
     * Test for WalletPayment creation with a null Wallet.
     */
    @Test
    void testWalletPaymentWithNullWallet();

    /**
     * Test for successfully processing a payment.
     */
    @Test
    void testProcessPayment() throws NotEnoughWalletException;

    /**
     * Test for processing a payment with insufficient funds.
     */
    @Test
    void testProcessPaymentWithInsufficientFunds();

}
