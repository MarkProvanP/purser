package scot.provan.purser.core;

import scot.provan.purser.core.exceptions.TransactionCreationException;
import scot.provan.purser.core.exceptions.TransactionAmountException;

/**
 * Created by Mark on 07/06/2015.
 */
public class Income extends Transaction {

    public Income(TransactionDataBundle bundle, User addedBy, Organisation org) throws TransactionCreationException {
        super(bundle, addedBy, org);
        if (bundle.getAmount() <= 0) throw new TransactionAmountException(bundle, 0, Double.MAX_VALUE, addedBy, org);
    }
}
