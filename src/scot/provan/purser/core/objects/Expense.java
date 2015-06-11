package scot.provan.purser.core.objects;

import scot.provan.purser.core.exceptions.PurserObjectNotFoundException;
import scot.provan.purser.core.exceptions.TransactionAmountException;

import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public class Expense extends Transaction {
    // TODO implement variable transaction min/max amount - per user/project?
    public static final double EXPENSE_MIN_AMOUNT = Double.MIN_VALUE;
    public static final double EXPENSE_MAX_AMOUNT = 0.0;

    public Expense(TransactionDataBundle bundle, UUID addedBy, Organisation org)
            throws TransactionAmountException, PurserObjectNotFoundException {
        super(bundle, addedBy, org);
        if (bundle.getAmount() >= 0) {
            throw new TransactionAmountException(bundle, EXPENSE_MIN_AMOUNT, EXPENSE_MAX_AMOUNT, addedBy, org);
        }
    }
}
