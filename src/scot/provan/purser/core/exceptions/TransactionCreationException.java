package scot.provan.purser.core.exceptions;

import scot.provan.purser.core.Organisation;
import scot.provan.purser.core.Trader;
import scot.provan.purser.core.User;

/**
 * Created by Mark on 07/06/2015.
 */
public abstract class TransactionCreationException extends Exception {
    private double amount;
    private Trader tradeWith;
    private User addedBy;
    private Organisation org;

    public TransactionCreationException(
            double amount,
            Trader tradeWith,
            User addedBy,
            Organisation org
    ) {
        super();
        this.amount = amount;
        this.tradeWith = tradeWith;
        this.addedBy = addedBy;
        this.org = org;
    }

}
