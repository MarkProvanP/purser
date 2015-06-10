package scot.provan.purser.testing;

import org.junit.Before;
import org.junit.Test;
import scot.provan.purser.core.objects.Organisation;
import scot.provan.purser.core.objects.User;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Mark on 08/06/2015.
 */
public class ProjectTest {

    @Test
    public static void TestOrganisationCreation() {
        Organisation org1 = new Organisation();
        assertNotNull(org1);
    }

    @Test
    public static void TestUserCreation() {
        Organisation org1 = new Organisation();
        User.UserDataBundle user1data = new User.UserDataBundle();
        user1data.setFirstname("Test")
                .setLastname("User");
        UUID user1UUID = org1.createUser(user1data);
        User user1;
    }

}