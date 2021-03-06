/**
 * Tests the User Model class
 * 
 * @author Soumya Bagade
 */

package bco.scheduler.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
    /**
     * Tests that the fields of the User object is null if the constructor parameters are empty
     * (the assumption is that if one is null, then all would be)
     */
	@Test
	public void testNullConstructor() {
        User testUser = new User();
        assertNull(testUser.getFirstName());
    }

    /**
     * Tests that the User object has been made through the constructor
     */
	@Test
	public void testConstructor() {
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");
        assertNotNull(testUser);
    }
    
    /**
     * tests the getUsername() method
     */
    @Test
	public void testGetUsername() {
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");
        assertEquals(testUser.getUsername(), "jdoe");
    }
    
    /**
     * tests the setUsername() method
     */
    @Test
	public void testSetUsername() {
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");
        testUser.setUsername("johndoe");
        assertEquals(testUser.getUsername(), "johndoe");
    }

    /**
     * tests the getPasswordHash() method
     */
    @Test
	public void testGetPasswordHash() {
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");
        assertEquals(testUser.getPasswordHash(), "securepassword");
    }
    
    /**
     * tests the setPasswordHash() method
     */
    @Test
	public void testSetPasswordHash() {
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");
        testUser.setPasswordHash("M0r3SecureP@ssw0rd!");
        assertEquals(testUser.getPasswordHash(), "M0r3SecureP@ssw0rd!");
	}
}
