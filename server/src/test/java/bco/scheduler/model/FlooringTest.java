/**
 * Tests the Flooring Model class
 * 
 * @author Soumya Bagade, Will Duke
 */

package bco.scheduler.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlooringTest {
    /**
     * Tests that the fields of the Flooring object is null if the constructor parameters are empty
     * (the assumption is that if one is null, then all would be)
     */
	@Test
	public void testNullConstructor() {
        Flooring testFlooring = new Flooring();
        assertNull(testFlooring.getEmail());
    }

    /**
     * Tests that the Flooring object has been made through the constructor
     */
	@Test
	public void testConstructor() {
        Flooring testFlooring = new Flooring("Hardwood");
        assertNotNull(testFlooring);
    }

    /**
     * tests the getID() and setID() methods
     */
    @Test
    public void testID(){
        Flooring testFlooring = new Flooring("Vinyl");
        testFlooring.setId(1);
        assertEquals(testFlooring.getId(), 1);
    }
    
    /**
     * tests the getName() method
     */
    @Test
	public void testGetName() {
        Flooring testFlooring = new Flooring("Bamboo");
        assertEquals(testFlooring.getName(), "Bamboo");
    }
    
    /**
     * tests the setName() method
     */
    @Test
	public void testName() {
        Flooring testFlooring = new Flooring("Tile");
        testFlooring.setName("Not tile");
        assertEquals(testFlooring.getFirstName(), "Not tile");
       
    }
}
