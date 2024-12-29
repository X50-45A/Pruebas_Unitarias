package micromobility.payment;

import exceptions.NotEnoughWalletException;

import java.math.BigDecimal;

public abstract class Payment {
    private final String serviceID;
    private final BigDecimal amount;

    public Payment(String serviceID, BigDecimal amount) {
        if (serviceID == null || serviceID.isEmpty() || amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid serviceID or amount");
        }
        this.serviceID = serviceID;
        this.amount = amount;
    }

    public String getServiceID() {
        return serviceID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public abstract void processPayment() throws NotEnoughWalletException;
}

