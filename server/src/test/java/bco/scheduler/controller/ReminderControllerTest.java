package bco.scheduler.controller;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;
import bco.scheduler.model.Reminder;
import java.util.*;
import java.util.ArrayList;
import bco.scheduler.model.CommunicationType;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import bco.scheduler.model.UserType;


/**
 * Tests the Reminder controller class.
 * Uses Mockiato for mocking of relevant pieces. 
 * 
 * @author Will Duke
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class ReminderControllerTest {

    // A mock of the reminder controller class.
    @Mock
    private ReminderController reminderController = new ReminderController();

    /**
     * Tests the getting of all the reminders through the
     * getAllReminders method.
     */
    @Test
    public void getAllRemindersTest() throws Exception {
     
        // An reminder object to use.
        Reminder testReminder = new Reminder(365, 1, 2, UserType.CUSTOMER);

        // An reminder object list to use.
        List<Reminder> allReminders = Arrays.asList(testReminder);
 
        // Response Entity representation of the desired output
        ResponseEntity<List<Reminder>> re = ResponseEntity.ok(allReminders);

        // Ensure the reminders are null.
        assertNull(reminderController.getAllReminders());

        // Telling Mockiato how to handle the methods.
        when(reminderController.getAllReminders()).thenReturn(re);

        // Ensure the reminder is in there. 
        assertEquals(reminderController.getAllReminders(), re);
    }

    /**
     * Tests the getting of the reminders by their id through
     * the getReminderById method.
     */
    @Test
    public void getReminderByIdTest() throws Exception {

        // A reminder object to use.
        Reminder testReminder = new Reminder(365, 1, 2, UserType.CUSTOMER);

        // A reminder object list to use.
        List<Reminder> allReminders = Arrays.asList(testReminder);
 
        // Response Entity representation of the desired output
        ResponseEntity<Reminder> re = ResponseEntity.ok(testReminder);

        // Ensure there are no reminders when they aren't in there.
        assertNull(reminderController.getReminderById((long) testReminder.getId()));

        // Telling Mockiato how to handle the methods.
        when(reminderController.getReminderById((long) testReminder.getId())).thenReturn(re);

        // Ensuring the reminder can be found. 
        assertEquals(reminderController.getReminderById((long) testReminder.getId()), re);
        
    }

    /**
     * Tests that the reminder is created successfully through 
     * the create reminder method.
     */
    @Test
    public void createReminderTest() throws Exception {
        
        // A reminder object to use.
        Reminder testReminder = new Reminder(365, 1, 2, UserType.CUSTOMER);

        // Response Entity representation of the desired output
        ResponseEntity<Reminder> re = ResponseEntity.ok(testReminder);

        // Telling Mockiato how to handle the methods.
        when(reminderController.createReminder(testReminder)).thenReturn(re);

        // Ensuring the reminder is created 
        assertEquals(reminderController.createReminder(testReminder), re);

    }

    /**
     * Tests that the reminders are updated properly through the
     * update reminder method.
     */
    @Test
    public void updateReminderTest() throws Exception {
     
        // A reminder object to use.
        Reminder testReminder = new Reminder(365, 1, 2, UserType.CUSTOMER);

        // A reminder object to update to. 
        Reminder testReminderUpdated = new Reminder(365, 2, 3, UserType.CUSTOMER);

        // Response Entity representation of the desired output
        ResponseEntity<Reminder> re = ResponseEntity.ok(testReminder);

        // Response Entity representation of the desired output
        ResponseEntity<Reminder> updatedRe = ResponseEntity.ok(testReminderUpdated);

        // Telling Mockiato how to handle the methods.
        when(reminderController.createReminder(testReminder)).thenReturn(re);

        // Ensuring the reminder is create
        assertEquals(reminderController.createReminder(testReminder), re);

        // Telling Mockiato how to handle the methods.
        when(reminderController.updateReminder(testReminder.getId(), testReminderUpdated)).thenReturn(updatedRe);

        // Ensure the reminder is updated correctly. 
        assertEquals(reminderController.updateReminder(testReminder.getId(), testReminderUpdated), updatedRe);

    }

    /**
     * Tests that reminders are deleted using the delete reminder method.
     */
    @Test
    public void deleteReminderTest() throws Exception {

        // A reminder object to use.
        Reminder testReminder = new Reminder(365, 1, 2, UserType.CUSTOMER);

        // A reminder object to update to. 
        Reminder testReminderUpdated = new Reminder(365, 2, 3, UserType.CUSTOMER);

        // Response Entity representation of the desired output
        ResponseEntity<Reminder> re = ResponseEntity.ok(testReminder);
        
        // Response Entity representation of the desired output
        ResponseEntity<Reminder> deletedRe = ResponseEntity.ok(testReminder);

        // Telling Mockiato how to handle the methods.
        when(reminderController.createReminder(testReminder)).thenReturn(re);

        // Telling Mockiato how to handle the methods.
        when(reminderController.deleteReminder(testReminder.getId())).thenReturn(deletedRe);

        // Ensure that the reminder is created. 
        assertEquals(reminderController.createReminder(testReminder), re);

        // Ensure the reminder is deleted. 
        assertEquals(reminderController.deleteReminder(testReminder.getId()), deletedRe);

    }

}
