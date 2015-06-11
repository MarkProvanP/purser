package scot.provan.purser.core.exceptions;

import scot.provan.purser.core.objects.Transaction;

import java.util.UUID;

/**
 * Created by Mark on 08/06/2015.
 */
public class TransactionNotFoundException extends PurserObjectNotFoundException {
    public TransactionNotFoundException(UUID transactionUUID) {
        super(transactionUUID, Transaction.class);
    }
}
