/**
 * Tests the Carrier Model class
 * 
 * @author Will Duke
 */

package bco.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CarrierTest {

    Carrier test = new Carrier("Verizon", "verizon@gmail.com");

    /**
     * Tests that the Carrier object has been made through the constructor
     */
	@Test
	public void testConstructor() {
       assertNotNull(test);
    }
 
    /**
     * Tests the getter and setter methods for the id
     */
    @Test
    public void testGetAndSetId() {
        test.setId(1);
        assertEquals(1, test.getId());
    }

    /**
     * Tests the getter and setter methods for the name
     */
    @Test
    public void testGetAndSetName() {
        assertEquals("Verizon", test.getName());
        test.setName("AT&T");
        assertEquals("AT&T", test.getName());
    }

    /**
     * Tests the getter and setter methods for the email domain
     */
    @Test
    public void testGetAndSetEmailDomain() {
        assertEquals("verizon@gmail.com", test.getEmailDomain());
        test.setEmailDomain("att@gmail.com");
        assertEquals("att@gmail.com", test.getEmailDomain());
    }
}
