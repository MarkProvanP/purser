package scot.provan.purser.core;

import java.time.LocalDateTime;

/**
 * Created by Mark on 07/06/2015.
 */
public class Trader {
    private static int numberCreated;
    private int identifier;
    private String name;
    private User addedBy;
    private Organisation org;
    private LocalDateTime added;

    public Trader(String name, User addedBy, Organisation org) {
        if (name == null) throw new NullPointerException("Name is null.");
        if (addedBy == null) throw new NullPointerException("User is null.");
        if (org == null) throw new NullPointerException("Organisation is null.");

        this.identifier = numberCreated;
        numberCreated++;
        this.name = name;
        this.addedBy = addedBy;
        this.org = org;
        this.added = LocalDateTime.now();
    }
}
