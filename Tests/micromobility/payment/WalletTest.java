package micromobility.payment;

import exceptions.NotEnoughWalletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

class WalletTest {
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
    void testWalletCreation() {
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    void testWalletCreationWithNegativeBalance() {
        assertThrows(IllegalArgumentException.class,
                () -> new Wallet(new BigDecimal("-100.00")));
    }

    @Test
    void testWalletCreationWithNullBalance() {
        assertThrows(IllegalArgumentException.class,
                () -> new Wallet(null));
    }

    @Test
    void testDeductWithSufficientFunds() throws NotEnoughWalletException {
        wallet.deduct(validAmount);
        assertEquals(new BigDecimal("50.00"), wallet.getBalance());
    }

    @Test
    void testDeductWithInsufficientFunds() {
        assertThrows(NotEnoughWalletException.class,
                () -> wallet.deduct(excessiveAmount));
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    void testDeductWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> wallet.deduct(new BigDecimal("-10.00")));
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    void testDeductWithZeroAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> wallet.deduct(BigDecimal.ZERO));
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    void testDeductWithNullAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> wallet.deduct(null));
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    void testAddFunds() {
        wallet.addFunds(validAmount);
        assertEquals(new BigDecimal("150.00"), wallet.getBalance());
    }

    @Test
    void testAddFundsWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> wallet.addFunds(new BigDecimal("-10.00")));
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    void testAddFundsWithZeroAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> wallet.addFunds(BigDecimal.ZERO));
        assertEquals(initialBalance, wallet.getBalance());
    }

    @Test
    void testAddFundsWithNullAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> wallet.addFunds(null));
        assertEquals(initialBalance, wallet.getBalance());
    }
}