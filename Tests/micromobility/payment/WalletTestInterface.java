package micromobility.payment;

import exceptions.NotEnoughWalletException;
import org.junit.jupiter.api.Test;

public interface WalletTestInterface {

    /**
     * Test for creating a wallet with a valid initial balance.
     */
    @Test
    void testWalletCreation();

    /**
     * Test for creating a wallet with a negative balance.
     */
    @Test
    void testWalletCreationWithNegativeBalance();

    /**
     * Test for creating a wallet with a null balance.
     */
    @Test
    void testWalletCreationWithNullBalance();

    /**
     * Test for deducting funds with sufficient balance.
     */
    @Test
    void testDeductWithSufficientFunds() throws NotEnoughWalletException;

    /**
     * Test for deducting funds with insufficient balance.
     */
    @Test
    void testDeductWithInsufficientFunds();

    /**
     * Test for deducting a negative amount.
     */
    @Test
    void testDeductWithNegativeAmount();

    /**
     * Test for deducting a zero amount.
     */
    @Test
    void testDeductWithZeroAmount();

    /**
     * Test for deducting a null amount.
     */
    @Test
    void testDeductWithNullAmount();

    /**
     * Test for adding valid funds to the wallet.
     */
    @Test
    void testAddFunds();

    /**
     * Test for adding negative funds to the wallet.
     */
    @Test
    void testAddFundsWithNegativeAmount();

    /**
     * Test for adding zero funds to the wallet.
     */
    @Test
    void testAddFundsWithZeroAmount();

    /**
     * Test for adding null funds to the wallet.
     */
    @Test
    void testAddFundsWithNullAmount();
}
