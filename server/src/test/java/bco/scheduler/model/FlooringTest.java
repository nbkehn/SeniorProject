/**
 * Tests the FlooringType Model class
 * 
 * @author Soumya Bagade, Will Duke
 */

package bco.scheduler.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlooringTest {
    /**
     * Tests that the fields of the FlooringType object is null if the constructor parameters are empty
     * (the assumption is that if one is null, then all would be)
     */
	@Test
	public void testNullConstructor() {
        FlooringType testFlooringType = new FlooringType();
        assertNull(testFlooringType.getName());
    }

    /**
     * Tests that the FlooringType object has been made through the constructor
     */
	@Test
	public void testConstructor() {
        FlooringType testFlooringType = new FlooringType("Hardwood");
        assertNotNull(testFlooringType);
    }

    /**
     * tests the getID() and setID() methods
     */
    @Test
    public void testID(){
        FlooringType testFlooringType = new FlooringType("Vinyl");
        testFlooringType.setId(1);
        assertEquals(testFlooringType.getId(), 1);
    }
    
    /**
     * tests the getName() method
     */
    @Test
	public void testGetName() {
        FlooringType testFlooringType = new FlooringType("Bamboo");
        assertEquals(testFlooringType.getName(), "Bamboo");
    }
    
    /**
     * tests the setName() method
     */
    @Test
	public void testName() {
        FlooringType testFlooringType = new FlooringType("Tile");
        testFlooringType.setName("Not tile");
        assertEquals(testFlooringType.getName(), "Not tile");
       
    }
}
