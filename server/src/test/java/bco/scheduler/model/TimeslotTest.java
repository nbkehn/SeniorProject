/**
 * Tests the Timeslot Model class
 * 
 * @author Will Duke
 */

package bco.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import org.junit.Test;

public class TimeslotTest {
    
    ZonedDateTime testStartTime = ZonedDateTime.of(2018, 01, 01, 1, 0, 0, 0, ZoneId.of("UTC"));

    ZonedDateTime testEndTime = ZonedDateTime.of(2018, 01, 01, 2, 0, 0, 0, ZoneId.of("UTC"));

    ZonedDateTime testStartTime2 = ZonedDateTime.of(2018, 01, 01, 5, 0, 0, 0, ZoneId.of("UTC"));

    ZonedDateTime testEndTime2 = ZonedDateTime.of(2018, 01, 01, 7, 0, 0, 0, ZoneId.of("UTC"));

    /**
     * Tests that the Timeslot object has been made through the constructor
     */
	@Test
	public void testConstructor() {
       Timeslot test = new Timeslot(testStartTime, testEndTime);
       assertNotNull(test);
    }
 
    /**
     * Tests the setter and getter for start date time
     */
	@Test
	public void testGetAndSetStartDateTime() {
        Timeslot test = new Timeslot(testStartTime, testEndTime);
        assertEquals(test.getStartDateTime(), testStartTime);
        test.setStartDateTime(testStartTime2);
        assertEquals(test.getStartDateTime(), testStartTime2);
    }

    /**
     * Tests the setter and getter for end date time
     */
	@Test
	public void testGetAndSetEndDateTime() {
        Timeslot test = new Timeslot(testStartTime, testEndTime);
        assertEquals(test.getEndDateTime(), testEndTime);
        test.setEndDateTime(testEndTime2);
        assertEquals(test.getEndDateTime(), testEndTime2);
    }
}
