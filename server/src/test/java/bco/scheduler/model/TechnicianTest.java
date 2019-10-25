/**
 * Tests the Technician Model class
 * 
 * @author Soumya Bagade
 */

package bco.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

class TechnicianTest {

    /**
     * Tests that the Technician object has been made through the constructor
     */
	@Test
	void testConstructor() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertNotNull(testTechnician);
    }

    /**
     * tests the getID() and setID() methods
     */
    @Test
    void testID(){
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        testTechnician.setId(1);
        assertEquals(testTechnician.getId(), 1);
    }
    
    /**
     * tests the getFirstName() method
     */
    @Test
	void testGetFirstName() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertEquals(testTechnician.getFirstName(), "jdoe");
    }
    
    /**
     * tests the setFirstName() method
     */
    @Test
	void testSetFirstName() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        testTechnician.setFirstName("Joe");
        assertEquals(testTechnician.getFirstName(), "Joe");
    }

    /**
     * tests the getLastName() method
     */
    @Test
	void testGetLastName() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertEquals(testTechnician.getLastName(), "Doe");
    }
    
    /**
     * tests the setLastName() method
     */
    @Test
	void testSetLastName() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        testTechnician.setLastName("Deer");
        assertEquals(testTechnician.getLastName(), "Deer");
    }

    /**
     * tests the getEmail() method
     */
    @Test
	void testGetEmail() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertEquals(testTechnician.getEmail(), "jdoe@gmail.com");
    }
    
    /**
     * tests the setEmail() method
     */
    @Test
	void testSetEmail() { 
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        testTechnician.setEmail("johndoe@gmail.com");
        assertEquals(testTechnician.getEmail(), "johndoe@gmail.com");
    }

    /**
     * tests the getPhone() method
     */
    @Test
	void testGetPhone() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertEquals(testTechnician.getPhone(), "919-515-3000");
    }
    
    /**
     * tests the setPhone() method
     */
    @Test
	void testSetPhone() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        testTechnician.setPhone("678-999-8212");
        assertEquals(testTechnician.getPhone(), "678-999-8212");
    }

     /**
     * tests the isInHouse() method
     */
    @Test
	void testIsInHouse() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertTrue(testTechnician.isInHouse());
    }
    
    /**
     * tests the setInHouse() method
     */
    @Test
	void testSetInHouse() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertTrue(testTechnician.isInHouse());
        testTechnician.setInHouse(false);
        assertFalse(testTechnician.isInHouse());
    }
}
