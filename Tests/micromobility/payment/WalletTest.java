package micromobility.payment;

import exceptions.NotEnoughWalletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

class WalletTest implements WalletTestInterface {
    private Wallet wallet;
    private BigDecimal initialBalance;
    private BigDecimal validAmount;
    private BigDecimal excessiveAmount;

    @BeforeEach
    void setUp() {
        initialBalance = new BigDecimal("100.00");
        wallet = new Wallet(initialBalance);
        validAmount = new BigDecimal("50.00");
        excessiveAmount = new BigDecimal("150.00");
    }

    @Test
    public void testWalletCreation() {
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    public void testWalletCreationWithNegativeBalance() {
        assertThrows(IllegalArgumentException.class,
                () -> new Wallet(new BigDecimal("-100.00")));
    }

    @Test
    public void testWalletCreationWithNullBalance() {
        assertThrows(IllegalArgumentException.class,
                () -> new Wallet(null));
    }

    @Test
    public void testDeductWithSufficientFunds() throws NotEnoughWalletException {
        wallet.deduct(validAmount);
        assertEquals(new BigDecimal("50.00"), wallet.getBalance());
    }

    @Test
    public void testDeductWithInsufficientFunds() {
        assertThrows(NotEnoughWalletException.class,
                () -> wallet.deduct(excessiveAmount));
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    public void testDeductWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> wallet.deduct(new BigDecimal("-10.00")));
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    public void testDeductWithZeroAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> wallet.deduct(BigDecimal.ZERO));
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    public void testDeductWithNullAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> wallet.deduct(null));
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    public void testAddFunds() {
        wallet.addFunds(validAmount);
        assertEquals(new BigDecimal("150.00"), wallet.getBalance());
    }

    @Test
    public void testAddFundsWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> wallet.addFunds(new BigDecimal("-10.00")));
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    public void testAddFundsWithZeroAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> wallet.addFunds(BigDecimal.ZERO));
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    public void testAddFundsWithNullAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> wallet.addFunds(null));
        assertEquals(initialBalance, wallet.getBalance());
    }
}