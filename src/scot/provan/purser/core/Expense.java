package scot.provan.purser.core;

import scot.provan.purser.core.exceptions.TransactionAmountException;

/**
 * Created by Mark on 07/06/2015.
 */
public class Expense extends Transaction {
    public Expense(double amount, Trader tradeWith, User addedBy, Organisation org) throws TransactionAmountException {
        super(amount, tradeWith, addedBy, org);
        if (amount >= 0) throw new TransactionAmountException(amount, Double.MIN_VALUE, 0, tradeWith, addedBy, org);
    }
}
