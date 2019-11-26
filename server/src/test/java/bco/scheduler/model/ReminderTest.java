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
<<<<<<< HEAD
        Reminder testReminder = new Reminder();
=======
        Reminder testReminder = new Reminder(365, 1, 2, UserType.CUSTOMER);
>>>>>>> d1b27dca487b352477feab1ebe643c777c5509a0
        assertNotNull(testReminder);
    }
    

    /**
     * tests the getID() and setID() methods
     */
    @Test
    public void testID(){
<<<<<<< HEAD
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
=======
        Reminder testReminder = new Reminder(365, 1, 2, UserType.CUSTOMER);
>>>>>>> d1b27dca487b352477feab1ebe643c777c5509a0
        testReminder.setId(1);
        assertEquals(testReminder.getId(), 1);
    }
    

    /**
     * tests the getTimeToSend() method
     */
    @Test
	public void testGetTimeToSend() {
<<<<<<< HEAD
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
        assertEquals(testReminder.getTimeToSend(), TimeToSend.ONE_YEAR_AFTER);
=======
        Reminder testReminder = new Reminder(365, 1, 2, UserType.CUSTOMER);
        assertEquals(testReminder.getTimeToSend(), 365);
>>>>>>> d1b27dca487b352477feab1ebe643c777c5509a0
    }
    
    /**
     * tests the setTimeToSend() method
     */
    @Test
	public void testSetTimeToSend() {
<<<<<<< HEAD
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
        assertEquals(testReminder.getTimeToSend(), TimeToSend.ONE_YEAR_AFTER);
        testReminder.setTimeToSend(TimeToSend.ONE_WEEK_PRIOR);
        assertEquals(testReminder.getTimeToSend(), TimeToSend.ONE_WEEK_PRIOR);
=======
        Reminder testReminder = new Reminder(365, 1, 2, UserType.CUSTOMER);
        assertEquals(testReminder.getTimeToSend(), 365);
        testReminder.setTimeToSend(-7);
        assertEquals(testReminder.getTimeToSend(), -7);
>>>>>>> d1b27dca487b352477feab1ebe643c777c5509a0
    }

    /**
     * tests the getTextTemplate() method
     */
    @Test
<<<<<<< HEAD
	public void testGetTextTemplate() {
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
        assertEquals(testReminder.getTextTemplate(), textTemplate);
=======
	public void testGetTextTemplateId() {
        Reminder testReminder = new Reminder(365, 1, 2, UserType.CUSTOMER);
        assertEquals(testReminder.getTextTemplateId(), 1);
>>>>>>> d1b27dca487b352477feab1ebe643c777c5509a0
    }
    
    /**
     * tests the setTextTemplate() method
     */
    @Test
<<<<<<< HEAD
	public void testSetTextTemplate() throws ResourceNotFoundException {
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
        assertEquals(testReminder.getTextTemplate(), textTemplate);
        
        Template newTextTemplate = new Template("TestTitle3", "TestSubject3", "TestContent3");
        testReminder.setTextTemplate(newTextTemplate);
        assertEquals(testReminder.getTextTemplate(), newTextTemplate);
=======
	public void testSetTextTemplateId() {
        Reminder testReminder = new Reminder(365, 1, 2, UserType.CUSTOMER);
        assertEquals(testReminder.getTextTemplateId(), 1);
        testReminder.setTextTemplateId(100);
        assertEquals(testReminder.getTextTemplateId(), 100);
>>>>>>> d1b27dca487b352477feab1ebe643c777c5509a0
    }
    
    /**
     * tests the getEmailTemplate() method
     */
    @Test
<<<<<<< HEAD
	public void testGetEmailTemplate() {
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
        assertEquals(testReminder.getEmailTemplate(), emailTemplate);
=======
	public void testGetEmailTemplateId() {
        Reminder testReminder = new Reminder(365, 1, 2, UserType.CUSTOMER);
        assertEquals(testReminder.getEmailTemplateId(), 2);
>>>>>>> d1b27dca487b352477feab1ebe643c777c5509a0
    }
    
    /**
     * tests the setEmailTemplate() method
     */
    @Test
<<<<<<< HEAD
	public void testSetEmailTemplate() throws ResourceNotFoundException {
        Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER, textTemplate, emailTemplate);
        assertEquals(testReminder.getEmailTemplate(), emailTemplate);
        Template newEmailTemplate = new Template("TestTitle4", "TestSubject4", "TestContent4");
        testReminder.setEmailTemplate(newEmailTemplate);
        assertEquals(testReminder.getEmailTemplate(), newEmailTemplate);
=======
	public void testSetEmailTemplateId() {
        Reminder testReminder = new Reminder(365, 1, 2, UserType.CUSTOMER);
        assertEquals(testReminder.getEmailTemplateId(), 2);
        testReminder.setEmailTemplateId(200);
        assertEquals(testReminder.getEmailTemplateId(), 200);
>>>>>>> d1b27dca487b352477feab1ebe643c777c5509a0
	}
}
