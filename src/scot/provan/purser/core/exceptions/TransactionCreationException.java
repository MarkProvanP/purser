package scot.provan.purser.core.exceptions;

import scot.provan.purser.core.objects.Organisation;
import scot.provan.purser.core.objects.Transaction;
import scot.provan.purser.core.objects.User;

import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public abstract class TransactionCreationException extends Exception {
    public Transaction.TransactionDataBundle getBundle() {
        return bundle;
    }

    public UUID getAddedBy() {
        return addedBy;
    }

    public Organisation getOrg() {
        return org;
    }

    private Transaction.TransactionDataBundle bundle;
    private UUID addedBy;
    private Organisation org;

    public TransactionCreationException(
            Transaction.TransactionDataBundle bundle,
            UUID addedBy,
            Organisation org
    ) {
        super();
        this.bundle = bundle;
        this.addedBy = addedBy;
        this.org = org;
    }

}
