package scot.provan.purser.core.objects;

/**
 * Created by Mark on 07/06/2015.
 */
public class User extends PurserObject {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public User(UserDataBundle bundle, Organisation org) {
        super(org);

        if (bundle == null) throw new NullPointerException("User data bundle is null.");

        this.firstName = bundle.getFirstName();
        this.lastName = bundle.getLastName();
    }

    public static class UserDataBundle {
        public String getFirstName() {
            return firstName;
        }

        public UserDataBundle setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public UserDataBundle setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        private String firstName;
        private String lastName;

    }

    public String getDetails() {
        return String.format("START USER | UUID: %s, First name: %s, Last name: %s | END USER",
                super.getUUID(), firstName, lastName);
    }
}
