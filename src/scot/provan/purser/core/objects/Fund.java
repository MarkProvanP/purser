package scot.provan.purser.core.objects;

import scot.provan.purser.core.PurserCommon;
import scot.provan.purser.core.exceptions.PurserObjectNotFoundException;
import scot.provan.purser.core.exceptions.TransactionNotFoundException;
import scot.provan.purser.core.exceptions.UserNotFoundException;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by Mark on 10/06/2015.
 */
public class Fund extends PurserObject {
    private UUID addedBy;
    private Organisation org;

    private String name;
    private Collection<UUID> transactions;

    public Fund(FundDataBundle bundle, UUID addedBy, Organisation org) throws PurserObjectNotFoundException {
        super();

        if (bundle == null) throw new NullPointerException("Fund bundle is null");
        if (addedBy == null) throw new NullPointerException("Fund user is null");
        if (org == null) throw new NullPointerException("Fund org is null");

        this.addedBy = addedBy;
        this.org = org;

        // Have to test that the provided addedBy User UUID does exist in the Organisation's list of users.
        try {
            org.getUser(this.addedBy);
        } catch (UserNotFoundException e) {
            PurserCommon.log(PurserCommon.LogLevel.INFO,
                    String.format("Error when creating Fund: %s - Fund creator User UUID not found.",
                            e.getMessage()));
            throw e;
        }

        this.name = bundle.getName();
        this.transactions = bundle.getTransactions();

        // Have to test that each of the provided transactions' Transaction UUID does exist in the Organisation's list of transactions.
        for (UUID transactionsUUID : this.transactions) {
            try {
                org.getTransaction(transactionsUUID);
            } catch (TransactionNotFoundException e) {
                PurserCommon.log(PurserCommon.LogLevel.INFO,
                        String.format("Error when creating Fund: %s - Fund transactions' Transaction UUID not found.",
                                e.getMessage()));
                throw e;
            }
        }
    }

    public static class FundDataBundle {
        private String name;
        private Collection<UUID> transactions;

        public String getName() {
            return name;
        }

        public FundDataBundle setName(String name) {
            this.name = name;
            return this;
        }

        public Collection<UUID> getTransactions() {
            return transactions;
        }

        public FundDataBundle setTransactions(Collection<UUID> transactions) {
            this.transactions = transactions;
            return this;
        }
    }
}
