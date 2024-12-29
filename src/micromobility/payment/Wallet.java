package micromobility.payment;

import exceptions.NotEnoughWalletException;

import java.math.BigDecimal;

public class Wallet {
    private BigDecimal balance;

    public Wallet(BigDecimal initialBalance) {
        if (initialBalance == null || initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid initial balance");
        }
        this.balance = initialBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deduct(BigDecimal amount) throws NotEnoughWalletException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        if (balance.compareTo(amount) < 0) {
            throw new NotEnoughWalletException("Insufficient balance in wallet");
        }
        balance = balance.subtract(amount);
    }

    public void addFunds(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        balance = balance.add(amount);
    }
}
