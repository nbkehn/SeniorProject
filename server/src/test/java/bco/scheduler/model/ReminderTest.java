/**
 * Tests the Reminder Model class
 * 
 * @author Soumya Bagade
 */

package bco.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

class ReminderTest {

    /**
     * Tests that the Reminder object has been made through the constructor
     */
	@Test
	void testConstructor() {
        Reminder testReminder = new Reminder(365, 1, 2);
        assertNotNull(testReminder);
    }
    

    /**
     * tests the getID() and setID() methods
     */
    @Test
    void testID(){
        Reminder testReminder = new Reminder(365, 1, 2);
        testReminder.setId(1);
        assertEquals(testReminder.getId(), 1);
    }
    

    /**
     * tests the getTimeToSend() method
     */
    @Test
	void testGetTimeToSend() {
        Reminder testReminder = new Reminder(365, 1, 2);
        assertEquals(testReminder.getTimeToSend(), 365);
    }
    
    /**
     * tests the setTimeToSend() method
     */
    @Test
	void testSetTimeToSend() {
        Reminder testReminder = new Reminder(365, 1, 2);
        assertEquals(testReminder.getTimeToSend(), 365);
        testReminder.setTimeToSend(-7);
        assertEquals(testReminder.getTimeToSend(), -7);
    }

    /**
     * tests the getTextTemplateId() method
     */
    @Test
	void testGetTextTemplateId() {
        Reminder testReminder = new Reminder(365, 1, 2);
        assertEquals(testReminder.getTextTemplateId(), 1);
    }
    
    /**
     * tests the setTextTemplateId() method
     */
    @Test
	void testSetTextTemplateId() {
        Reminder testReminder = new Reminder(365, 1, 2);        
        assertEquals(testReminder.getTextTemplateId(), 1);
        testReminder.setTextTemplateId(100);
        assertEquals(testReminder.getTextTemplateId(), 100);
    }
    
    /**
     * tests the getEmailTemplateId() method
     */
    @Test
	void testGetEmailTemplateId() {
        Reminder testReminder = new Reminder(365, 1, 2);
        assertEquals(testReminder.getEmailTemplateId(), 2);
    }
    
    /**
     * tests the setEmailTemplateId() method
     */
    @Test
	void testSetEmailTemplateId() {
        Reminder testReminder = new Reminder(365, 1, 2);
        assertEquals(testReminder.getEmailTemplateId(), 2);
        testReminder.setEmailTemplateId(200);
        assertEquals(testReminder.getEmailTemplateId(), 200);
	}
}
