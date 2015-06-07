package scot.provan.purser.core.objects;

import scot.provan.purser.core.exceptions.TransactionAmountException;

import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public class Expense extends Transaction {
    public Expense(TransactionDataBundle bundle, UUID addedBy, Organisation org) throws TransactionAmountException {
        super(bundle, addedBy, org);
        if (bundle.getAmount() >= 0) throw new TransactionAmountException(bundle, Double.MIN_VALUE, 0, addedBy, org);
    }
}
