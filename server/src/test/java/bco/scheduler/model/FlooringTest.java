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
        assertNull(testFlooringType.getStyle());
        assertNull(testFlooringType.getColor());
        assertNotNull(testFlooringType.getId());
    }

    /**
     * Tests that the full constructor processes hardwood and non-hardwood flooring types
     * correctly (hardwood should have all three parameters recorded while others should only
     * have name and style recorded).
     */
    @Test
    public void testFullConstructor() {
        // Test with all parameters w/ hardwood
        FlooringType f1 = new FlooringType("Hardwood", "Oak", "Red");
        assertEquals(f1.getName(), "Hardwood");
        assertEquals(f1.getStyle(), "Oak");
        assertEquals(f1.getColor(), "Red");
        //Test with first two parameters
        FlooringType f2 = new FlooringType("Carpet", "Fuzzy", null);
        assertEquals(f2.getName(), "Carpet");
        assertEquals(f2.getStyle(), "Fuzzy");
        assertNull(f2.getColor());
        //Test with all parameters, not hardwood
        FlooringType f3 = new FlooringType("Carpet", "Fuzzy", "Cream");
        assertEquals(f3.getName(), "Carpet");
        assertEquals(f3.getStyle(), "Fuzzy");
        assertNull(f3.getColor());
    }

    /**
     * tests the getID() and setID() methods
     */
    @Test
    public void testID(){
        FlooringType testFlooringType = new FlooringType();
        testFlooringType.setId(1);
        assertEquals(testFlooringType.getId(), 1);
    }
    
    /**
     * tests the getName() method
     */
    @Test
	public void testGetName() {
        FlooringType testFlooringType = new FlooringType();
        testFlooringType.setName("Bamboo");
        assertEquals(testFlooringType.getName(), "Bamboo");
    }
    
    /**
     * tests the setName() method
     */
    @Test
	public void testGetStyle() {
        FlooringType testFlooringType = new FlooringType();
        testFlooringType.setStyle("Vinyl");
        assertEquals(testFlooringType.getStyle(), "Vinyl");
       
    }
}
