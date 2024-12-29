package micromobility.payment;

import exceptions.NotEnoughWalletException;

import java.math.BigDecimal;

public class WalletPayment extends Payment {
    private final Wallet wallet;

    public WalletPayment(String serviceID, BigDecimal amount, Wallet wallet) {
        super(serviceID, amount);
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet cannot be null");
        }
        this.wallet = wallet;
    }

    @Override
    public void processPayment() throws NotEnoughWalletException {
        wallet.deduct(getAmount());
    }
}
