package scot.provan.purser.core.exceptions;

import scot.provan.purser.core.Organisation;
import scot.provan.purser.core.Trader;
import scot.provan.purser.core.Transaction;
import scot.provan.purser.core.User;

/**
 * Created by Mark on 07/06/2015.
 */
public abstract class TransactionCreationException extends Exception {
    private Transaction.TransactionDataBundle bundle;
    private User addedBy;
    private Organisation org;

    public TransactionCreationException(
            Transaction.TransactionDataBundle bundle,
            User addedBy,
            Organisation org
    ) {
        super();
        this.bundle = bundle;
        this.addedBy = addedBy;
        this.org = org;
    }

}
