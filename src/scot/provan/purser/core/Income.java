package scot.provan.purser.core;

import scot.provan.purser.core.exceptions.TransactionCreationException;
import scot.provan.purser.core.exceptions.TransactionAmountException;

/**
 * Created by Mark on 07/06/2015.
 */
public class Income extends Transaction {

    public Income(double amount, Trader tradeWith, User addedBy, Organisation org) throws TransactionCreationException {
        super(amount, tradeWith, addedBy, org);
        if (amount <= 0) throw new TransactionAmountException(amount, 0, Double.MAX_VALUE, tradeWith, addedBy, org);
    }
}
