package scot.provan.purser.core.objects;

import scot.provan.purser.core.exceptions.PurserObjectNotFoundException;
import scot.provan.purser.core.exceptions.TransactionCreationException;
import scot.provan.purser.core.exceptions.TransactionAmountException;

import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public class Income extends Transaction {
    // TODO implement variable transaction min/max amount - per user/project?
    public static final double INCOME_MIN_AMOUNT = 0.0;
    public static final double INCOME_MAX_AMOUNT = Double.MAX_VALUE;

    public Income(TransactionDataBundle bundle, UUID addedBy, Organisation org)
            throws TransactionCreationException, PurserObjectNotFoundException {
        super(bundle, addedBy, org);
        if (bundle.getAmount() <= 0) {
            throw new TransactionAmountException(bundle, INCOME_MIN_AMOUNT, INCOME_MAX_AMOUNT, addedBy, org);
        }
    }
}
