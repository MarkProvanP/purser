package scot.provan.purser.core.objects;

import scot.provan.purser.core.PurserCommon;
import scot.provan.purser.core.exceptions.PurserObjectNotFoundException;
import scot.provan.purser.core.exceptions.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public class Trader extends PurserObject {
    private UUID addedBy;
    private LocalDateTime added;

    private String name;

    public Trader(TraderDataBundle bundle, UUID addedBy, Organisation org) throws PurserObjectNotFoundException {
        super(org);

        if (bundle == null) throw new NullPointerException("Trader data bundle is null.");
        if (addedBy == null) throw new NullPointerException("User is null.");

        this.addedBy = addedBy;

        // Have to test that the provided addedBy User UUID does exist in the Organisation's list of users.
        try {
            org.getUser(this.addedBy);
        } catch (UserNotFoundException e) {
            PurserCommon.log(PurserCommon.LogLevel.INFO,
                    String.format("Error when creating Trader: %s - Trader creator User UUID not found.",
                            e.getMessage()));
            throw e;
        }

        this.added = LocalDateTime.now();

        this.name = bundle.getName();
    }

    public static class TraderDataBundle {
        private String name;


        public String getName() {
            return name;
        }

        public TraderDataBundle setName(String name) {
            this.name = name;
            return this;
        }
    }

    public String getDetails() {
        String addedByString = "UNKNOWN";
        try {
            User addedByUser = super.getOrg().getUser(addedBy);
            addedByString = addedByUser.getDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.format("START TRADER | UUID: %s, Added by: %s, Name: %s | END TRADER\n",
                super.getUUID(), addedByString, name);
    }
}
