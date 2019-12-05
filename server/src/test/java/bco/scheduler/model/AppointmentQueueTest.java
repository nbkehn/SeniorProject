/**
 * Tests the AppointmentQueue Model class
 * 
 * @author Will Duke
 */

package bco.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class AppointmentQueueTest {

    AppointmentQueue test = new AppointmentQueue(1, 2);

    /**
     * Tests that the AppointmentQueue object has been made through the constructor
     */
	@Test
	public void testConstructor() {
       assertNotNull(test);
    }

    /**
     * Tests the getter and setter methods for the id of the appointment queue
     */
    @Test
    public void testGetAndSetId() {
        test.setId(7);
        assertEquals(7, test.getId());
    }

    /**
     * Tests the getter and setter methods for the appointment id
     */
    @Test
    public void testGetAndSetAppointmentId() {
        assertEquals(1, test.getAppointmentId());
        test.setAppointmentId(4);
        assertEquals(4, test.getAppointmentId());
    }

    /**
     * Tests the getter and setter methods for the reminder id
     */
    @Test
    public void testGetAndSetReminderId() {
        assertEquals(2, test.getReminderId());
        test.setReminderId(6);
        assertEquals(6, test.getReminderId());
    }

    /**
     * Tests the getter and setter methods for the isSent variable
     */
    @Test
    public void testGetAndSetIsSent() {
        test.setSent(true);
        assertTrue(test.isSent());
        test.setSent(false);
        assertFalse(test.isSent());
    }

    /**
     * Tests the getter and setter methods for the error message.
     */
    @Test
    public void testGetAndSetErrorMessage() {
        test.setErrorMessage("Generic error message.");
        assertEquals("Generic error message.", test.getErrorMessage());
    }

}
