/**
 * Tests the AppointmentQueue Model class
 * 
 * @author Will Duke
 */

package bco.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.Set;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.time.Month;
import java.util.Arrays;


import org.junit.Test;

public class AppointmentQueueTest {

    CommunicationType communicationType = CommunicationType.EMAIL;
    CommunicationType communicationType2 = CommunicationType.TEXT;
    Customer customer = new Customer("Noah", "Trimble", "ntrimbl@ncsu.edu", "0987654321", communicationType, "123 St NC");
    Customer customer2 = new Customer("Soumya", "Bagade", "sbagade@ncsu.edu", "5432167890", communicationType2, "321 St NC");
    Technician tech = new Technician("Bob", "Dylan", "bob@gmail.com", "0192837465", true);
    Technician tech2 = new Technician("Jane", "Doe", "janedoe@gmail.com", "2143658709", false);
    Technician tech3 = new Technician("John", "Doe", "johndoe@gmail.com", "3216549870", false);
    Technician array[] = { tech, tech2 };
    Technician array2[] = { tech2, tech3 };
    Set<Technician> technicians = new HashSet<Technician>(Arrays.asList(array));
    Set<Technician> technicians2 = new HashSet<Technician>(Arrays.asList(array2));
    FlooringType flooringtype = new FlooringType("hardwood", null, null);
    FlooringType flooringtype2 = new FlooringType("tile", null, null);
    LocalDateTime startDateTime = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
    LocalDateTime startDateTime2 = LocalDateTime.of(2015, Month.JUNE, 25, 12, 30, 40);
    LocalDateTime endDateTime = LocalDateTime.of(2015, Month.SEPTEMBER, 29, 19, 30, 40);
    LocalDateTime endDateTime2 = LocalDateTime.of(2015, Month.DECEMBER, 25, 12, 30, 40);
    
    RSA rsa = new RSA(); 
    // rsa.setFirstName("Will");
    // rsa.setLastName("Duke");
    // rsa.setEmail("wfduke@ncsu.edu");
    // rsa.setPhone("3363440576");
    Appointment testAppointment = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);
    
     // Text in the reminder
     private Template textTemplate = new Template("TestTitle1", "TestSubject1", "TestContent1");
    
     // Email in the reminder 
     private Template emailTemplate = new Template("TestTitle2", "TestSubject2", "TestContent2");
 

    // A test reminder object to use
    Reminder testReminder = new Reminder(TimeToSend.ONE_YEAR_AFTER.getOffset(), textTemplate, emailTemplate, UserType.RSA);

    AppointmentQueue aqTest = new AppointmentQueue(testAppointment, testReminder);

    /**
     * Tests that the AppointmentQueue object has been made through the constructor
     */
	@Test
	public void testConstructor() {
       assertNotNull(aqTest);
    }

    /**
     * Tests the getter and setter methods for the id of the appointment queue
     */
    @Test
    public void testGetAndSetId() {
        aqTest.setId(7);
        assertEquals(7, aqTest.getId());
    }

    /**
     * Tests the getter and setter methods for the appointment id
     */
    @Test
    public void testGetAndSetAppointment() {
        aqTest.setAppointment(testAppointment);
        assertEquals(testAppointment, aqTest.getAppointment());
    }

    /**
     * Tests the getter and setter methods for the reminder id
     */
    @Test
    public void testGetAndSetReminderId() {
        aqTest.setReminder(testReminder);
        assertEquals(testReminder, aqTest.getReminder());
    }

    /**
     * Tests the getter and setter methods for the isSent variable
     */
    @Test
    public void testGetAndSetIsSent() {
        aqTest.setSent(true);
        assertTrue(aqTest.isSent());
        aqTest.setSent(false);
        assertFalse(aqTest.isSent());
    }

    /**
     * Tests the getter and setter methods for the error message.
     */
    @Test
    public void testGetAndSetErrorMessage() {
        aqTest.setErrorMessage("Generic error message.");
        assertEquals("Generic error message.", aqTest.getErrorMessage());
    }

}
