package scot.provan.purser.core.objects;

import java.util.UUID;

/**
 * Created by Mark on 13/06/2015.
 */
public class Refund extends PurserObject {
    private UUID addedBy;

    private UUID expenseUUID;
    private String shortDesc;
    private String longDesc;
    private String retailer;
    private UUID refundUser;
    private UUID project;

    public Refund(RefundDataBundle bundle, UUID addedBy, Organisation org) {
        super(org);

        if (bundle == null) throw new NullPointerException("Bundle is null");
        if (addedBy == null) throw new NullPointerException("User is null");

        this.addedBy = addedBy;

        this.expenseUUID = bundle.getExpenseUUID();
        this.shortDesc = bundle.getShortDesc();
        this.longDesc = bundle.getLongDesc();
        this.retailer = bundle.getRetailer();
        this.refundUser = bundle.getRefundUser();
    }

    public static class RefundDataBundle {
        public UUID getExpenseUUID() {
            return expenseUUID;
        }

        public RefundDataBundle setExpenseUUID(UUID expenseUUID) {
            this.expenseUUID = expenseUUID;
            return this;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public RefundDataBundle setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
            return this;
        }

        public String getLongDesc() {
            return longDesc;
        }

        public RefundDataBundle setLongDesc(String longDesc) {
            this.longDesc = longDesc;
            return this;
        }

        public UUID getRefundUser() {
            return refundUser;
        }

        public RefundDataBundle setRefundUser(UUID refundUser) {
            this.refundUser = refundUser;
            return this;
        }

        public String getRetailer() {
            return retailer;
        }

        public RefundDataBundle setRetailer(String retailer) {
            this.retailer = retailer;
            return this;
        }

        public UUID getProject() {
            return project;
        }

        public RefundDataBundle setProject(UUID project) {
            this.project = project;
            return this;
        }

        private UUID expenseUUID;
        private String shortDesc;
        private String longDesc;
        private UUID refundUser;
        private String retailer;
        private UUID project;
    }

    public String getDetails() {
        String addedByString = "UNKNOWN";
        try {
            User addedByUser = super.getOrg().getUser(addedBy);
            addedByString = addedByUser.getDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.format("START REFUND | UUID: %s, Added by: %s, Short Desc: %s | END REFUND\n",
                super.getUUID(), addedByString, shortDesc);
    }
}
