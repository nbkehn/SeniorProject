/**
 * Tests the UserType Model class
 * 
 * @author Will Duke
 */

package bco.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class UserTypeTest {

    /**
     * Tests that the UserType object has been made through the constructor
     */
	@Test
	public void testConstructor() {
       UserType test = UserType.CUSTOMER;
       assertNotNull(test);
    }
 
    /**
     * Tests the getName() method
     */
	@Test
	public void testGetName() {
       // test customer
       UserType testEmail = UserType.CUSTOMER;
       assertEquals(testEmail.getName(), "Customer");

       // test rsa
       UserType testText = UserType.RSA;
       assertEquals(testText.getName(), "RSA");

       // test technician
       UserType testEmailAndText = UserType.TECHNICIAN;
       assertEquals(testEmailAndText.getName(), "Technician");

    }
}
