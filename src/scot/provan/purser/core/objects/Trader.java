package scot.provan.purser.core.objects;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public class Trader extends PurserObject {
    private UUID uuid;
    private UUID addedBy;
    private Organisation org;
    private LocalDateTime added;

    private String name;

    public Trader(TraderDataBundle bundle, UUID addedBy, Organisation org) {
        super();

        if (bundle == null) throw new NullPointerException("Trader data bundle is null.");
        if (addedBy == null) throw new NullPointerException("User is null.");
        if (org == null) throw new NullPointerException("Organisation is null.");

        this.addedBy = addedBy;
        this.org = org;
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
}
