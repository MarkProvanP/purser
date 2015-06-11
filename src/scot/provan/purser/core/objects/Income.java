package scot.provan.purser.core.objects;

import scot.provan.purser.core.exceptions.PurserObjectNotFoundException;
import scot.provan.purser.core.exceptions.TransactionCreationException;
import scot.provan.purser.core.exceptions.TransactionAmountException;

import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public class Income extends Transaction {

    public Income(TransactionDataBundle bundle, UUID addedBy, Organisation org)
            throws TransactionCreationException, PurserObjectNotFoundException {
        super(bundle, addedBy, org);
        if (bundle.getAmount() <= 0) throw new TransactionAmountException(bundle, 0, Double.MAX_VALUE, addedBy, org);
    }
}
