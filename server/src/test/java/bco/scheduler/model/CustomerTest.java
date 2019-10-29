/**
 * Tests the Customer Model class
 * 
 * @author Soumya Bagade
 */

package bco.scheduler.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {
    /**
     * Tests that the fields of the Customer object is null if the constructor parameters are empty
     * (the assumption is that if one is null, then all would be)
     */
	@Test
	public void testNullConstructor() {
        Customer testCustomer = new Customer();
        assertNull(testCustomer.getEmail());
    }

    /**
     * Tests that the Customer object has been made through the constructor
     */
	@Test
	public void testConstructor() {
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        assertNotNull(testCustomer);
    }

    /**
     * tests the getID() and setID() methods
     */
    @Test
    public void testID(){
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        testCustomer.setId(1);
        assertEquals(testCustomer.getId(), 1);
    }
    
    /**
     * tests the getFirstName() method
     */
    @Test
	public void testGetFirstName() {
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        assertEquals(testCustomer.getFirstName(), "John");
    }
    
    /**
     * tests the setFirstName() method
     */
    @Test
	public void testSetFirstName() {
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        testCustomer.setFirstName("Joe");
        assertEquals(testCustomer.getFirstName(), "Joe");
       
    }

    /**
     * tests the getLastName() method
     */
    @Test
	public void testGetLastName() {
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        assertEquals(testCustomer.getLastName(), "Doe");
    }
    
    /**
     * tests the setLastName() method
     */
    @Test
	public void testSetLastName() {
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        testCustomer.setLastName("Deer");
        assertEquals(testCustomer.getLastName(), "Deer");
    }

    /**
     * tests the getEmail() method
     */
    @Test
	public void testGetEmail() {
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        assertEquals(testCustomer.getEmail(), "jdoe@gmail.com");
    }
    
    /**
     * tests the setEmail() method
     */
    @Test
	public void testSetEmail() { 
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        testCustomer.setEmail("johndoe@gmail.com");
        assertEquals(testCustomer.getEmail(), "johndoe@gmail.com");
    }

    /**
     * tests the getPhone() method
     */
    @Test
	public void testGetPhone() {
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        assertEquals(testCustomer.getPhone(), "919-515-3000");
    }
    
    /**
     * tests the setPhone() method
     */
    @Test
	public void testSetPhone() {
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        testCustomer.setPhone("678-999-8212");
        assertEquals(testCustomer.getPhone(), "678-999-8212");
    }

     /**
     * tests the getCommunicationPreference() method
     */
    @Test
	public void testGetCommunicationPreference() {
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        assertEquals(testCustomer.getCommunicationPreference(), CommunicationType.EMAIL_AND_TEXT);
    }
    
    /**
     * tests the setCommunicationPreference() method
     */
    @Test
	public void testSetCommunicationPreference() {
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        testCustomer.setCommunicationPreference(CommunicationType.EMAIL);
        assertEquals(testCustomer.getCommunicationPreference(), CommunicationType.EMAIL);
    }

     /**
     * tests the getAddress() method
     */
    @Test
	public void testGetAddress() {
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        assertEquals(testCustomer.getAddress(), "890 Oval Dr, Raleigh, NC 27606");
    }
    
    /**
     * tests the setAddress() method
     */
    @Test
	public void testSetAddress() {
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
        testCustomer.setAddress("110000023 Oval Dr, Raleigh, NC 27606");
        assertEquals(testCustomer.getAddress(), "110000023 Oval Dr, Raleigh, NC 27606");
    }
}
