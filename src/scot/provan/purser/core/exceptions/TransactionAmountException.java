package scot.provan.purser.core.exceptions;

import scot.provan.purser.core.objects.Organisation;
import scot.provan.purser.core.objects.Trader;
import scot.provan.purser.core.objects.Transaction;
import scot.provan.purser.core.objects.User;

import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public class TransactionAmountException extends TransactionCreationException {
    private double minimumAllowed;
    private double maximumAllowed;
    private String message;

    public TransactionAmountException(
            Transaction.TransactionDataBundle bundle,
            double minimumAllowed,
            double maximumAllowed,
            UUID addedBy,
            Organisation org
    ) {
        super(bundle, addedBy, org);
        this.minimumAllowed = minimumAllowed;
        this.maximumAllowed = maximumAllowed;
        if (bundle.getAmount() <= minimumAllowed) {
            message = String.format("Transaction of %d is less than minimum allowed of: %d", bundle.getAmount(), this.minimumAllowed);
        } else if (bundle.getAmount() >= maximumAllowed) {
            message = String.format("Transaction of %d is more than maximum allowed of: %d", bundle.getAmount(), this.maximumAllowed);
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}
