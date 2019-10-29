/**
 * Tests the Reminder Model class
 * 
 * @author Soumya Bagade
 */

package bco.scheduler.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReminderTest {

    /**
     * Tests that the Reminder object is Null if the reminder is not set with parameters
     * (the assumption is that if one is null, then all would be)
     */
	@Test
	public void testNullConstructor() {
        Reminder nullReminder = new Reminder();
        assertEquals(nullReminder.getEmailTemplateId(), 0);
    }

    /**
     * Tests that the Reminder object has been made through the constructor
     */
	@Test
	public void testConstructor() {        
        Reminder testReminder = new Reminder(365, 1, 2);
        assertNotNull(testReminder);
    }
    

    /**
     * tests the getID() and setID() methods
     */
    @Test
    public void testID(){
        Reminder testReminder = new Reminder(365, 1, 2);
        testReminder.setId(1);
        assertEquals(testReminder.getId(), 1);
    }
    

    /**
     * tests the getTimeToSend() method
     */
    @Test
	public void testGetTimeToSend() {
        Reminder testReminder = new Reminder(365, 1, 2);
        assertEquals(testReminder.getTimeToSend(), 365);
    }
    
    /**
     * tests the setTimeToSend() method
     */
    @Test
	public void testSetTimeToSend() {
        Reminder testReminder = new Reminder(365, 1, 2);
        assertEquals(testReminder.getTimeToSend(), 365);
        testReminder.setTimeToSend(-7);
        assertEquals(testReminder.getTimeToSend(), -7);
    }

    /**
     * tests the getTextTemplateId() method
     */
    @Test
	public void testGetTextTemplateId() {
        Reminder testReminder = new Reminder(365, 1, 2);
        assertEquals(testReminder.getTextTemplateId(), 1);
    }
    
    /**
     * tests the setTextTemplateId() method
     */
    @Test
	public void testSetTextTemplateId() {
        Reminder testReminder = new Reminder(365, 1, 2);        
        assertEquals(testReminder.getTextTemplateId(), 1);
        testReminder.setTextTemplateId(100);
        assertEquals(testReminder.getTextTemplateId(), 100);
    }
    
    /**
     * tests the getEmailTemplateId() method
     */
    @Test
	public void testGetEmailTemplateId() {
        Reminder testReminder = new Reminder(365, 1, 2);
        assertEquals(testReminder.getEmailTemplateId(), 2);
    }
    
    /**
     * tests the setEmailTemplateId() method
     */
    @Test
	public void testSetEmailTemplateId() {
        Reminder testReminder = new Reminder(365, 1, 2);
        assertEquals(testReminder.getEmailTemplateId(), 2);
        testReminder.setEmailTemplateId(200);
        assertEquals(testReminder.getEmailTemplateId(), 200);
	}
}
