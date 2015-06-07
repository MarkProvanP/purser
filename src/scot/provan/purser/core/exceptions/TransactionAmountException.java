package scot.provan.purser.core.exceptions;

import scot.provan.purser.core.Organisation;
import scot.provan.purser.core.Trader;
import scot.provan.purser.core.User;

/**
 * Created by Mark on 07/06/2015.
 */
public class TransactionAmountException extends TransactionCreationException {
    private double amount;
    private double minimumAllowed;
    private double maximumAllowed;
    private Trader tradeWith;

    public TransactionAmountException(
            double amount,
            double minimumAllowed,
            double maximumAllowed,
            Trader tradeWith,
            User addedBy,
            Organisation org
    ) {
        super(amount, tradeWith, addedBy, org);
        this.minimumAllowed = minimumAllowed;
        this.maximumAllowed = maximumAllowed;
    }

    @Override
    public String getMessage() {
        return String.format("Expense of %d to %s cannot be positive.", amount, tradeWith.toString());
    }
}
