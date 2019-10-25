/**
 * Tests the User Model class
 * 
 * @author Soumya Bagade
 */

package bco.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

class UserTest {

    /**
     * Tests that the User object has been made through the constructor
     */
	@Test
	void testConstructor() {
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");
        assertNotNull(testUser);
    }
    
    /**
     * tests the getUsername() method
     */
    @Test
	void testGetUsername() {
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");
        assertEquals(testUser.getUsername(), "jdoe");
    }
    
    /**
     * tests the setUsername() method
     */
    @Test
	void testSetUsername() {
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");
        testUser.setUsername("johndoe");
        assertEquals(testUser.getUsername(), "johndoe");
    }

    /**
     * tests the getPasswordHash() method
     */
    @Test
	void testGetPasswordHash() {
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");
        assertEquals(testUser.getPasswordHash(), "securepassword");
    }
    
    /**
     * tests the setPasswordHash() method
     */
    @Test
	void testSetPasswordHash() {
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");
        testUser.setPasswordHash("M0r3SecureP@ssw0rd!");
        assertEquals(testUser.getPasswordHash(), "M0r3SecureP@ssw0rd!");
	}
}
