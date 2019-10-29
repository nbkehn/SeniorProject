/**
 * Tests the Technician Model class
 * 
 * @author Soumya Bagade
 */

package bco.scheduler.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TechnicianTest {
    
    /**
     * Tests that the fields of the Technician object is null if the constructor parameters are empty
     * (the assumption is that if one is null, then all would be)
     */
	@Test
	public void testNullConstructor() {
        Technician nullTechnician = new Technician();
        assertNull(nullTechnician.getFirstName());
    }

    /**
     * Tests that the Technician object has been made through the constructor
     */
	@Test
	public void testConstructor() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertNotNull(testTechnician);
    }

    /**
     * tests the getID() and setID() methods
     */
    @Test
    public void testID(){
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        testTechnician.setId(1);
        assertEquals(testTechnician.getId(), 1);
    }
    
    /**
     * tests the getFirstName() method
     */
    @Test
	public void testGetFirstName() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertEquals(testTechnician.getFirstName(), "John");
    }
    
    /**
     * tests the setFirstName() method
     */
    @Test
	public void testSetFirstName() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        testTechnician.setFirstName("Joe");
        assertEquals(testTechnician.getFirstName(), "Joe");
    }

    /**
     * tests the getLastName() method
     */
    @Test
	public void testGetLastName() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertEquals(testTechnician.getLastName(), "Doe");
    }
    
    /**
     * tests the setLastName() method
     */
    @Test
	public void testSetLastName() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        testTechnician.setLastName("Deer");
        assertEquals(testTechnician.getLastName(), "Deer");
    }

    /**
     * tests the getEmail() method
     */
    @Test
	public void testGetEmail() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertEquals(testTechnician.getEmail(), "jdoe@gmail.com");
    }
    
    /**
     * tests the setEmail() method
     */
    @Test
	public void testSetEmail() { 
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        testTechnician.setEmail("johndoe@gmail.com");
        assertEquals(testTechnician.getEmail(), "johndoe@gmail.com");
    }

    /**
     * tests the getPhone() method
     */
    @Test
	public void testGetPhone() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertEquals(testTechnician.getPhone(), "919-515-3000");
    }
    
    /**
     * tests the setPhone() method
     */
    @Test
	public void testSetPhone() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        testTechnician.setPhone("678-999-8212");
        assertEquals(testTechnician.getPhone(), "678-999-8212");
    }

     /**
     * tests the isInHouse() method
     */
    @Test
	public void testIsInHouse() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertTrue(testTechnician.isInHouse());
    }
    
    /**
     * tests the setInHouse() method
     */
    @Test
	public void testSetInHouse() {
        Technician testTechnician = new Technician("John", "Doe", "jdoe@gmail.com", "919-515-3000", true);
        assertTrue(testTechnician.isInHouse());
        testTechnician.setInHouse(false);
        assertFalse(testTechnician.isInHouse());
    }
}
