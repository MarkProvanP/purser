package scot.provan.purser.core.objects;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Mark on 07/06/2015.
 */
public abstract class Transaction extends PurserObject {
    private static int numberCreated;
    private int identifier;
    private LocalDateTime added;
    private User addedBy;
    private Organisation org;
    private TransactionDataBundle bundle;

    public Transaction(TransactionDataBundle bundle, User addedBy, Organisation org) {
        super();

        if (bundle == null) throw new NullPointerException("Transaction data bundle is null.");
        if (addedBy == null) throw new NullPointerException("User is null.");
        if (org == null) throw new NullPointerException("Organisation is null.");

        this.identifier = numberCreated;
        numberCreated++;
        this.added = LocalDateTime.now();
        this.addedBy = addedBy;
        this.org = org;
        this.bundle = bundle;
    }

    public static class TransactionDataBundle {
        public String getShortDesc() {
            return shortDesc;
        }

        public TransactionDataBundle setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
            return this;
        }

        public String getLongDesc() {
            return longDesc;
        }

        public TransactionDataBundle setLongDesc(String longDesc) {
            this.longDesc = longDesc;
            return this;
        }

        public double getAmount() {
            return amount;
        }

        public TransactionDataBundle setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Trader getTradeWith() {
            return tradeWith;
        }

        public TransactionDataBundle setTradeWith(Trader tradeWith) {
            this.tradeWith = tradeWith;
            return this;
        }

        public LocalDateTime getTransactedDateTime() {
            return transactedDateTime;
        }

        public TransactionDataBundle setTransactedDateTime(LocalDateTime transactedDateTime) {
            this.transactedDateTime = transactedDateTime;
            return this;
        }

        public Collection<Project> getProjects() {
            return projects;
        }

        public TransactionDataBundle setProjects(Collection<Project> projects) {
            this.projects = projects;
            return this;
        }

        private String shortDesc;
        private String longDesc;
        private double amount;
        private Trader tradeWith;
        private LocalDateTime transactedDateTime;
        private Collection<Project> projects;
    }
}
