package scot.provan.purser.testing;

import org.junit.Test;
import scot.provan.purser.core.objects.Organisation;

import static org.junit.Assert.*;

/**
 * Created by Mark on 11/06/2015.
 */
public class OrganisationTest {
    @Test
    public void creation() {
        Organisation org1 = new Organisation();
        assertNotNull(org1);
    }

    @Test
    public void emptyLists() {
        Organisation org1 = new Organisation();
        assertEquals(0, org1.getUsers().size());
        assertEquals(0, org1.getProjects().size());
        assertEquals(0, org1.getTraders().size());
        assertEquals(0, org1.getTransactions().size());
        assertEquals(0, org1.getFunds().size());
    }
}
