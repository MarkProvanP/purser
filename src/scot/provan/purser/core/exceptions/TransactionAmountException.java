package scot.provan.purser.core.exceptions;

import scot.provan.purser.core.objects.Organisation;
import scot.provan.purser.core.objects.Trader;
import scot.provan.purser.core.objects.Transaction;
import scot.provan.purser.core.objects.User;

/**
 * Created by Mark on 07/06/2015.
 */
public class TransactionAmountException extends TransactionCreationException {
    private double amount;
    private double minimumAllowed;
    private double maximumAllowed;
    private Trader tradeWith;

    public TransactionAmountException(
            Transaction.TransactionDataBundle bundle,
            double minimumAllowed,
            double maximumAllowed,
            User addedBy,
            Organisation org
    ) {
        super(bundle, addedBy, org);
        this.minimumAllowed = minimumAllowed;
        this.maximumAllowed = maximumAllowed;
    }

    @Override
    public String getMessage() {
        return String.format("Expense of %d to %s cannot be positive.", amount, tradeWith.toString());
    }
}
