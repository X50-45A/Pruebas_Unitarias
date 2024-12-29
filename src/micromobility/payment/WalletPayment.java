package micromobility.payment;

import data.UserAccount;
import exceptions.NotEnoughWalletException;

import java.math.BigDecimal;

public class WalletPayment extends Payment {
    private final Wallet wallet;

    private final UserAccount userAccount;

    public WalletPayment(UserAccount userAccount, BigDecimal amount, Wallet wallet) {
        super(userAccount.getEmail(), amount);
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet cannot be null");
        }
        if (userAccount == null) {
            throw new IllegalArgumentException("UserAccount cannot be null");
        }
        this.wallet = wallet;
        this.userAccount = userAccount;
    }

    @Override
    public void processPayment() throws NotEnoughWalletException {
        wallet.deduct(getAmount());
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }
}
