package scot.provan.purser.core;

/**
 * Created by Mark on 07/06/2015.
 */
public class User {
    private static int numberCreated = 0;
    private int identifier;
    private String name;
    private Organisation org;

    public User(String name, Organisation org) {
        if (name == null) throw new NullPointerException("Name is null.");
        if (org == null) throw new NullPointerException("Organisation is null.");

        identifier = numberCreated;
        numberCreated++;
        this.name = name;
        this.org = org;
    }
}
