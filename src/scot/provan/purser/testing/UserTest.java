package scot.provan.purser.testing;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import scot.provan.purser.core.exceptions.UserNotFoundException;
import scot.provan.purser.core.objects.Organisation;
import scot.provan.purser.core.objects.User;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Mark on 11/06/2015.
 */
public class UserTest {
    private static Organisation org;

    @Before
    public void setUpOrganisation() {
        org = new Organisation();
    }

    @Test
    public void creationSingle() {
        String firstName = "Test";
        String lastName = "User";

        User.UserDataBundle user1data = new User.UserDataBundle();
        user1data.setFirstName(firstName)
                .setLastName(lastName);
        UUID user1UUID = org.createUser(user1data);
        User user1 = null;
        try {
             user1 = org.getUser(user1UUID);
        } catch (UserNotFoundException e) {
            fail();
        }

        assertEquals(firstName, user1.getFirstName());
        assertEquals(lastName, user1.getLastName());
        assertEquals(1, org.getUsers().size());
    }

    @Test
    public void creationMultiple() {
        int NUMBER_TO_CREATE = 5;
        HashMap<UUID, User> userUUIDMap = new HashMap<UUID, User>();
        for (int i = 0; i < NUMBER_TO_CREATE; i++) {
            String firstName = "Test" + i;
            String lastName = "User" + i;

            User.UserDataBundle userData = new User.UserDataBundle();
            userData.setFirstName(firstName)
                    .setLastName(lastName);
            UUID userUUID = org.createUser(userData);
            User user = null;
            try {
                user = org.getUser(userUUID);
            } catch (UserNotFoundException e) {
                fail();
            }
            userUUIDMap.put(userUUID, user);
        }
        assertEquals(NUMBER_TO_CREATE, org.getUsers().size());
        for (UUID userUUID : userUUIDMap.keySet()) {
            User user = null;
            try {
                user = org.getUser(userUUID);
            } catch (UserNotFoundException e) {
                fail();
            }
            assertEquals(userUUIDMap.get(userUUID), user);
        }
    }
}
