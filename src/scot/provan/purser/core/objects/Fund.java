package scot.provan.purser.core.objects;

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

    public Fund(FundDataBundle bundle, UUID addedBy, Organisation org) {
        super();

        if (bundle == null) throw new NullPointerException("Fund bundle is null");
        if (addedBy == null) throw new NullPointerException("Fund user is null");
        if (org == null) throw new NullPointerException("Fund org is null");

        this.addedBy = addedBy;
        this.org = org;

        this.name = bundle.getName();
        this.transactions = bundle.getTransactions();
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
