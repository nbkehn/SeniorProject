/**
 * Tests the RSA Model Object (and indirectly completely tests Person)
 * 
 * @author Soumya Bagade
 */

package bco.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import bco.scheduler.model.RSA;

public class RsaTest {

    /**
     * Tests that the RSA object has been made through the constructor
     */
	@Test
	public void testConstructor() {
        // initialize the RSA
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");
        assertNotNull(testRSA);
    }

    /**
     * tests the getID() and setID() methods
     */
    @Test
    public void testID(){
        // initialize the RSA
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");
        testRSA.setId(1);
        assertEquals(testRSA.getId(), 1);
    }
    
    /**
     * tests the getFirstName() method
     */
    @Test
	public void testGetFirstName() {
        // initialize the RSA
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");
        assertEquals(testRSA.getFirstName(), "John");
    }
    
    /**
     * tests the setFirstName() method
     */
    @Test
	public void testSetFirstName() {
        // initialize the RSA
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");
        // ensure first name saved
        assertEquals(testRSA.getFirstName(), "John");

        // test the change
        testRSA.setFirstName("Joe");
        assertEquals(testRSA.getFirstName(), "Joe");
    }

    /**
     * tests the getLastName() method
     */
    @Test
	public void testGetLastName() {
        // initialize the RSA 
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");
        assertEquals(testRSA.getLastName(), "Doe");
    }
    
    /**
     * tests the setLastName() method
     */
    @Test
	public void testSetLastName() {
        // initialize the RSA
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");
        // ensure last name saved
        assertEquals(testRSA.getLastName(), "Doe");

        // test the change
        testRSA.setLastName("Deer");
        assertEquals(testRSA.getLastName(), "Deer");
    }

    /**
     * tests the getEmail() method
     */
    @Test
	public void testGetEmail() {
        // initialize the RSA
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");
        assertEquals(testRSA.getEmail(), "jdoe@gmail.com");
    }
    
    /**
     * tests the setEmail() method
     */
    @Test
	public void testSetEmail() { 
        // initialize the RSA
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");
        // ensure email saved
        assertEquals(testRSA.getEmail(), "jdoe@gmail.com");

        // test the change
        testRSA.setEmail("johndoe@gmail.com");
        assertEquals(testRSA.getEmail(), "johndoe@gmail.com");
    }

    /**
     * tests the getPhone() method
     */
    @Test
	public void testGetPhone() {
        // initialize the RSA
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");
        assertEquals(testRSA.getPhone(), "919-515-3000");
    }
    
    /**
     * tests the setPhone() method
     */
    @Test
	public void testSetPhone() {
        // initialize the RSA
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");
        // ensure last name saved
        assertEquals(testRSA.getPhone(), "919-515-3000");

        // test the change
        testRSA.setPhone("678-999-8212");
        assertEquals(testRSA.getPhone(), "678-999-8212");
    }
}
