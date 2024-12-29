package micromobility.payment;

import exceptions.NotEnoughWalletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WalletPaymentTest implements WalletPaymentTestInterface {
    private WalletPayment walletPayment;
    private Wallet wallet;
    private String serviceID;
    private BigDecimal amount;

    @BeforeEach
    void setUp() {
        serviceID = "SERV123";
        amount = new BigDecimal("25.00");
        wallet = new Wallet(new BigDecimal("100.00"));
        walletPayment = new WalletPayment(serviceID, amount, wallet);
    }

    @Test
    public void testWalletPaymentCreation() {
        assertNotNull(walletPayment);
        assertEquals(serviceID, walletPayment.getServiceID());
        assertEquals(amount, walletPayment.getAmount());
    }

    @Test
    public void testWalletPaymentWithNullServiceID() {
        assertThrows(IllegalArgumentException.class,
                () -> new WalletPayment(null, amount, wallet));
    }

    @Test
    public void testWalletPaymentWithEmptyServiceID() {
        assertThrows(IllegalArgumentException.class,
                () -> new WalletPayment("", amount, wallet));
    }

    @Test
    public void testWalletPaymentWithNullAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new WalletPayment(serviceID, null, wallet));
    }

    @Test
    public void testWalletPaymentWithZeroAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new WalletPayment(serviceID, BigDecimal.ZERO, wallet));
    }

    @Test
    public void testWalletPaymentWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new WalletPayment(serviceID, new BigDecimal("-25.00"), wallet));
    }

    @Test
    public void testWalletPaymentWithNullWallet() {
        assertThrows(IllegalArgumentException.class,
                () -> new WalletPayment(serviceID, amount, null));
    }

    @Test
    public void testProcessPayment() throws NotEnoughWalletException {
        walletPayment.processPayment();
        assertEquals(new BigDecimal("75.00"), wallet.getBalance());
    }

    @Test
    public void testProcessPaymentWithInsufficientFunds() {
        WalletPayment largePayment = new WalletPayment(
                serviceID,
                new BigDecimal("150.00"),
                wallet
        );
        assertThrows(NotEnoughWalletException.class,
                largePayment::processPayment);
        assertEquals(new BigDecimal("100.00"), wallet.getBalance());
    }
}

