package scot.provan.purser.core.objects;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public abstract class Transaction extends PurserObject {
    private LocalDateTime added;
    private UUID addedBy;
    private Organisation org;
    private TransactionDataBundle bundle;

    public Transaction(TransactionDataBundle bundle, UUID addedBy, Organisation org) {
        super();

        if (bundle == null) throw new NullPointerException("Transaction data bundle is null.");
        if (addedBy == null) throw new NullPointerException("User is null.");
        if (org == null) throw new NullPointerException("Organisation is null.");

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

        public UUID getTradeWith() {
            return tradeWith;
        }

        public TransactionDataBundle setTradeWith(UUID tradeWith) {
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

        public Collection<UUID> getProjects() {
            return projects;
        }

        public TransactionDataBundle setProjects(Collection<UUID> projects) {
            this.projects = projects;
            return this;
        }

        private String shortDesc;
        private String longDesc;
        private double amount;
        private UUID tradeWith;
        private LocalDateTime transactedDateTime;
        private Collection<UUID> projects;
    }
}
