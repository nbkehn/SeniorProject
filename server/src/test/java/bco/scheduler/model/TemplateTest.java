/**
 * Tests the Template Model class
 * 
 * @author Soumya Bagade
 */

package bco.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

class TemplateTest {

    /**
     * Tests that the Template object has been made through the constructor
     */
	@Test
	void testConstructor() {
        Template testTemplate = new Template("title", "subject", "content");
        assertNotNull(testTemplate);
    }
    

    /**
     * tests the getID() and setID() methods
     */
    @Test
    void testID(){
        Template testTemplate = new Template("title", "subject", "content");
        testTemplate.setId(1);
        assertEquals(testTemplate.getId(), 1);
    }
    

    /**
     * tests the getTitle() method
     */
    @Test
	void testGetTitle() {
        Template testTemplate = new Template("title", "subject", "content");
        assertEquals(testTemplate.getTitle(), "title");
    }
    
    /**
     * tests the setTitle() method
     */
    @Test
	void testSetTitle() {
        Template testTemplate = new Template("title", "subject", "content");
        testTemplate.setTitle("new title");
        assertEquals(testTemplate.getTitle(), "new title");
    }

    /**
     * tests the getSubject() method
     */
    @Test
	void testGetSubject() {
        Template testTemplate = new Template("title", "subject", "content");
        assertEquals(testTemplate.getSubject(), "subject");
    }
    
    /**
     * tests the setSubject() method
     */
    @Test
	void testSetSubject() {
        Template testTemplate = new Template("title", "subject", "content");
        testTemplate.setSubject("new subject");
        assertEquals(testTemplate.getSubject(), "new subject");
    }
    
    /**
     * tests the getContent() method
     */
    @Test
	void testGetContent() {
        Template testTemplate = new Template("title", "subject", "content");
        assertEquals(testTemplate.getContent(), "content");
    }
    
    /**
     * tests the setContent() method
     */
    @Test
	void testSetContent() {
        Template testTemplate = new Template("title", "subject", "content");
        testTemplate.setContent("more content");
        assertEquals(testTemplate.getContent(), "more content");
	}
}
