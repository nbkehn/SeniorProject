/**
 * Tests the TimeToSend Model class
 * 
 * @author Soumya Bagade
 */

package bco.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TimeToSendTest {

    /**
     * Tests that the TimeToSend object has been made through the constructor
     */
	@Test
	public void testConstructor() {
        TimeToSend testTimeToSend = TimeToSend.ONE_DAY_PRIOR;
        assertNotNull(testTimeToSend);
    }
    
    /**
     * tests the getName() method
     */
    @Test
    public void testGetName(){
        // test one week prior
        TimeToSend testOneWeekPrior = TimeToSend.ONE_WEEK_PRIOR;
        assertEquals(testOneWeekPrior.getName(), "One Week Prior");

        // test one day prior
        TimeToSend testOneDayPrior = TimeToSend.ONE_DAY_PRIOR;
        assertEquals(testOneDayPrior.getName(), "One Day Prior");

        // test one year after
        TimeToSend testOneYearAfter = TimeToSend.ONE_YEAR_AFTER;
        assertEquals(testOneYearAfter.getName(), "One Year After");
    }
    

    /**
     * tests the getOffset() method
     */
    @Test
	public void testGetTimeToSend() {
        // test one week prior
        TimeToSend testOneWeekPrior = TimeToSend.ONE_WEEK_PRIOR;
        assertEquals(testOneWeekPrior.getOffset(), -7);

        // test one day prior
        TimeToSend testOneDayPrior = TimeToSend.ONE_DAY_PRIOR;
        assertEquals(testOneDayPrior.getOffset(), -1);

        // test one year after
        TimeToSend testOneYearAfter = TimeToSend.ONE_YEAR_AFTER;
        assertEquals(testOneYearAfter.getOffset(), 365);
    }
}
