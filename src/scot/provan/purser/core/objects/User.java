package scot.provan.purser.core.objects;

import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public class User extends PurserObject {
    private UserDataBundle bundle;
    private Organisation org;

    public User(UserDataBundle bundle, Organisation org) {
        super();

        if (bundle == null) throw new NullPointerException("User data bundle is null.");
        if (org == null) throw new NullPointerException("Organisation is null.");

        this.bundle = bundle;
        this.org = org;
    }

    public static class UserDataBundle {
        public String getFirstname() {
            return firstname;
        }

        public UserDataBundle setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public String getLastname() {
            return lastname;
        }

        public UserDataBundle setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        private String firstname;
        private String lastname;

    }
}
