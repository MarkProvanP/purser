package scot.provan.purser.testing;

import org.junit.BeforeClass;
import org.junit.Test;
import scot.provan.purser.core.exceptions.UserNotFoundException;
import scot.provan.purser.core.objects.Organisation;
import scot.provan.purser.core.objects.User;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Mark on 11/06/2015.
 */
public class UserTest {
    private static Organisation org;

    @BeforeClass
    public static void setUpOrganisation() {
        org = new Organisation();
    }

    @Test
    public void creation() {
        String firstName = "Test";
        String lastName = "User";

        Organisation org1 = new Organisation();
        User.UserDataBundle user1data = new User.UserDataBundle();
        user1data.setFirstName(firstName)
                .setLastName(lastName);
        UUID user1UUID = org1.createUser(user1data);
        User user1 = null;
        try {
             user1 = org1.getUser(user1UUID);
        } catch (UserNotFoundException e) {
            fail();
        }

        assertEquals(firstName, user1.getFirstName());
        assertEquals(lastName, user1.getLastName());
    }
}
