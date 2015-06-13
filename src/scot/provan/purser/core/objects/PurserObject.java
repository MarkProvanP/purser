package scot.provan.purser.core.objects;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public abstract class PurserObject {
    private UUID uuid;
    private LocalDateTime addedDateTime;
    private Organisation org;

    public UUID getUUID() {
        return uuid;
    }
    public LocalDateTime getAddedDateTime() {
        return addedDateTime;
    }
    public Organisation getOrg() {
        return org;
    }

    public PurserObject(Organisation org) {
        if (org == null) throw new NullPointerException("Organisation is null.");

        this.uuid = UUID.randomUUID();
        this.addedDateTime = LocalDateTime.now();
        this.org = org;
    }
}
