package micromobility.payment;

import data.UserAccount;
import exceptions.NotEnoughWalletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WalletPaymentTest implements WalletPaymentTestInterface {
    private WalletPayment walletPayment;
    private Wallet wallet;
    private UserAccount userAccount;
    private BigDecimal amount;

    @BeforeEach
    void setUp() {
        userAccount = new UserAccount("testUser@example.com");
        amount = new BigDecimal("25.00");
        wallet = new Wallet(new BigDecimal("100.00"));
        walletPayment = new WalletPayment(userAccount, amount, wallet);
    }

    @Test
    public void testWalletPaymentCreation() {
        assertNotNull(walletPayment);
        assertEquals(userAccount, walletPayment.getUserAccount());
        assertEquals(amount, walletPayment.getAmount());
    }


    @Test
    public void testWalletPaymentWithNullUserAccount() {
        assertThrows(NullPointerException.class,
                () -> new WalletPayment(null, amount, wallet));
    }

    @Test
    public void testWalletPaymentWithNullAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new WalletPayment(userAccount, null, wallet));
    }

    @Test
    public void testWalletPaymentWithZeroAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new WalletPayment(userAccount, BigDecimal.ZERO, wallet));
    }

    @Test
    public void testWalletPaymentWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new WalletPayment(userAccount, new BigDecimal("-25.00"), wallet));
    }

    @Test
    public void testWalletPaymentWithNullWallet() {
        assertThrows(IllegalArgumentException.class,
                () -> new WalletPayment(userAccount, amount, null));
    }

    @Test
    public void testProcessPayment() throws NotEnoughWalletException {
        walletPayment.processPayment();
        assertEquals(new BigDecimal("75.00"), wallet.getBalance());
    }

    @Test
    public void testProcessPaymentWithInsufficientFunds() {
        WalletPayment largePayment = new WalletPayment(
                userAccount,
                new BigDecimal("150.00"),
                wallet
        );
        assertThrows(NotEnoughWalletException.class,
                largePayment::processPayment);
        assertEquals(new BigDecimal("100.00"), wallet.getBalance());
    }
}

