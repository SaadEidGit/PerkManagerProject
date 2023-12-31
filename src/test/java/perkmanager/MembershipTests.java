package perkmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import perkmanager.Entity.Membership;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MembershipTests {

    @Test
    public void testCreateMembership() {
        Membership membership = new Membership();
        assertNotNull(membership.getPerkList());
        assertEquals(0, membership.getPerkList().size());
    }

    @Test
    public void testSetName() {
        Membership membership = new Membership();
        membership.setName("Gold");
        assertEquals("Gold", membership.getName());
    }
}
