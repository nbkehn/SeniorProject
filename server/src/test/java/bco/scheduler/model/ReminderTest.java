/**
 * Tests the Reminder Model class
 * 
 * @author Soumya Bagade
 */

package bco.scheduler.model;

import static org.junit.Assert.*;
import bco.scheduler.exception.ResourceNotFoundException;



import org.junit.Test;

public class ReminderTest {
       
    private Template textTemplate = new Template("TestTitle1", "TestSubject1", "TestContent1");
    private Template emailTemplate = new Template("TestTitle2", "TestSubject2", "TestContent2");
    
    /**
     * Tests that the Reminder object is Null if the reminder is not set with parameters
     * (the assumption is that if one is null, then all would be)
     */
	@Test
	public void testDefaultConstructor() {
        Reminder nullReminder = new Reminder();
        assertNull(nullReminder.getEmailTemplate());
    }

    /**
     * Tests that the Reminder object has been made through the constructor
     */
	@Test
	public void testConstructor() {        
        Reminder testReminder = new Reminder();
        assertNotNull(testReminder);
    }
    

    /**
     * tests the getID() and setID() methods
     */
    @Test
    public void testID(){
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
        testReminder.setId(1);
        assertEquals(testReminder.getId(), 1);
    }
    

    /**
     * tests the getTimeToSend() method
     */
    @Test
	public void testGetTimeToSend() {
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
        assertEquals(testReminder.getTimeToSend(), TimeToSend.ONE_YEAR_AFTER);
    }
    
    /**
     * tests the setTimeToSend() method
     */
    @Test
	public void testSetTimeToSend() {
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
        assertEquals(testReminder.getTimeToSend(), TimeToSend.ONE_YEAR_AFTER);
        testReminder.setTimeToSend(TimeToSend.ONE_WEEK_PRIOR);
        assertEquals(testReminder.getTimeToSend(), TimeToSend.ONE_WEEK_PRIOR);
    }

    /**
     * tests the getTextTemplate() method
     */
    @Test
	public void testGetTextTemplate() {
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
        assertEquals(testReminder.getTextTemplate(), textTemplate);
    }
    
    /**
     * tests the setTextTemplate() method
     */
    @Test
	public void testSetTextTemplate() throws ResourceNotFoundException {
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
        assertEquals(testReminder.getTextTemplate(), textTemplate);
        
        Template newTextTemplate = new Template("TestTitle3", "TestSubject3", "TestContent3");
        testReminder.setTextTemplate(newTextTemplate);
        assertEquals(testReminder.getTextTemplate(), newTextTemplate);
    }
    
    /**
     * tests the getEmailTemplate() method
     */
    @Test
	public void testGetEmailTemplate() {
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
        assertEquals(testReminder.getEmailTemplate(), emailTemplate);
    }
    
    /**
     * tests the setEmailTemplate() method
     */
    @Test
	public void testSetEmailTemplate() throws ResourceNotFoundException {
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
        assertEquals(testReminder.getEmailTemplate(), emailTemplate);
        Template newEmailTemplate = new Template("TestTitle4", "TestSubject4", "TestContent4");
        testReminder.setEmailTemplate(newEmailTemplate);
        assertEquals(testReminder.getEmailTemplate(), newEmailTemplate);
	}
}
