package scot.provan.purser.core.objects;

import scot.provan.purser.core.PurserCommon;
import scot.provan.purser.core.exceptions.FundNotFoundException;
import scot.provan.purser.core.exceptions.PurserObjectNotFoundException;
import scot.provan.purser.core.exceptions.TraderNotFoundException;
import scot.provan.purser.core.exceptions.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public abstract class Transaction extends PurserObject {
    public enum TransactionStatus {
        PROJECTED, CANCELLED, COMPLETED, REJECTED, UNKNOWN
    }

    private UUID addedBy;
    private Organisation org;

    private String shortDesc;
    private String longDesc;
    private double amount;
    private UUID tradeWith;
    private LocalDateTime transactedDateTime;
    private Collection<UUID> projects;
    private UUID orgFund;
    private TransactionStatus status;

    /**
     *
     * @param bundle Bundle containing the new Transaction's data fields
     * @param addedBy UUID of User who added the Transaction to the system
     * @param org Organisation object reference
     * @throws PurserObjectNotFoundException If any of the provided UUIDs are invalid
     */
    public Transaction(TransactionDataBundle bundle, UUID addedBy, Organisation org) throws PurserObjectNotFoundException {
        super();

        if (bundle == null) throw new NullPointerException("Transaction data bundle is null.");
        if (addedBy == null) throw new NullPointerException("User is null.");
        if (org == null) throw new NullPointerException("Organisation is null.");

        this.addedBy = addedBy;
        this.org = org;

        // Have to test that the provided addedBy User UUID does exist in the Organisation's list of users.
        try {
            org.getUser(this.addedBy);
        } catch (UserNotFoundException e) {
            PurserCommon.log(PurserCommon.LogLevel.INFO,
                    String.format("Error when creating Transaction: %s - Transaction creator User UUID not found.",
                            e.getMessage()));
            throw e;
        }

        this.shortDesc = bundle.getShortDesc();
        this.longDesc = bundle.getLongDesc();
        this.amount = bundle.getAmount();

        // Have to test that the provided tradeWith Trader UUID does exist in the Organisation's list of traders
        this.tradeWith = bundle.getTradeWith();
        try {
            org.getTrader(this.tradeWith);
        } catch (TraderNotFoundException e) {
            PurserCommon.log(PurserCommon.LogLevel.INFO,
                    String.format("Error when creating Transaction: %s - Transaction's Trader UUID not found",
                            e.getMessage()));
            throw e;
        }

        this.transactedDateTime = bundle.getTransactedDateTime();
        this.projects = bundle.getProjects();

        this.orgFund = bundle.getOrgFund();
        // Have to test that the provided orgFund Fund UUID does exist in the Organisation's list of traders
        try {
            org.getFund(this.orgFund);
        } catch (FundNotFoundException e) {
            PurserCommon.log(PurserCommon.LogLevel.INFO,
                    String.format("Error when creating Transaction: %s - Transaction's Fund UUID not found",
                            e.getMessage()));
            throw e;
        }

        this.status = bundle.getStatus();
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

        public UUID getOrgFund() {
            return orgFund;
        }

        public TransactionDataBundle setOrgFund(UUID orgFund) {
            this.orgFund = orgFund;
            return this;
        }

        public TransactionStatus getStatus() {
            return status;
        }

        public TransactionDataBundle setStatus(TransactionStatus status) {
            this.status = status;
            return this;
        }

        private String shortDesc;
        private String longDesc;
        private double amount;
        private UUID tradeWith;
        private LocalDateTime transactedDateTime;
        private Collection<UUID> projects;
        private UUID orgFund;
        private TransactionStatus status;
    }

    public String getDetails() {
        String addedByString = "UNKNOWN";
        try {
            User addedByUser = org.getUser(addedBy);
            addedByString = addedByUser.getDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String tradeWithString = "UNKNOWN";
        try {
            Trader tradeWithTrader = org.getTrader(tradeWith);
            tradeWithString = tradeWithTrader.getDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String orgFundString = "UNKNOWN";
        try {
            Fund orgFundFund = org.getFund(orgFund);
            orgFundString = orgFundFund.getDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.format("START TRANSACTION | UUID: %s, Added by: %s, Trade with: %s, Fund: %s, Amount: %.2f | END TRANSACTION",
                super.getUUID(), addedByString, tradeWithString, orgFundString, amount);
    }
}
