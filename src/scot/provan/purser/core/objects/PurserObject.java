package scot.provan.purser.core.objects;

import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public abstract class PurserObject {
    private UUID uuid;

    public UUID getUUID() {
        return uuid;
    }

    public PurserObject() {
        this.uuid = UUID.randomUUID();
    }
}
