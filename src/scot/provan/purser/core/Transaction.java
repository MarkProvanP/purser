package scot.provan.purser.core;

import java.time.LocalDateTime;

/**
 * Created by Mark on 07/06/2015.
 */
public abstract class Transaction {
    private static int numberCreated;
    private int identifier;
    private double amount;
    private LocalDateTime added;
    private LocalDateTime transacted;
    private Trader tradeWith;
    private User addedBy;
    private Organisation org;

    public Transaction(double amount, Trader tradeWith, User addedBy, Organisation org) {
        if (tradeWith == null) throw new NullPointerException("Trader is null.");
        if (addedBy == null) throw new NullPointerException("User is null.");
        if (org == null) throw new NullPointerException("Organisation is null.");

        this.identifier = numberCreated;
        numberCreated++;
        this.amount = amount;
        this.added = LocalDateTime.now();
        // TODO allow transactions to happen some other date.
        this.transacted = LocalDateTime.now();
        this.tradeWith = tradeWith;
        this.addedBy = addedBy;
        this.org = org;
    }
}
