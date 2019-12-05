/**
 * Tests the Person Model class
 * 
 * @author Will Duke
 */

package bco.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Map;
import java.util.HashMap;

import org.junit.Test;

public class PersonTest {

    Person testPerson = new Person("Will", "Duke", "wfduke@ncsu.edu", "3363440576");

    /**
     * Tests that the Person object has been made through the constructor
     */
	@Test
	public void testConstructor() {
        assertNotNull(testPerson);
    }
 
    /**
     * Tests the getter and setter methods for the first name.
     */
    @Test
    public void testGetAndSetFirstName() {
        assertEquals("Will", testPerson.getFirstName());
        testPerson.setFirstName("Connor");
        assertEquals("Connor", testPerson.getFirstName());
    }

    /**
     * Tests the getter and setter methods for the last name.
     */
    @Test
    public void testGetAndSetLastName() {
        assertEquals("Duke", testPerson.getLastName());
        testPerson.setLastName("Parke");
        assertEquals("Parke", testPerson.getLastName());
    }

    /**
     * Tests the getter and setter methods for the email.
     */
    @Test
    public void testGetAndSetEmail() {
        assertEquals("wfduke@ncsu.edu", testPerson.getEmail());
        testPerson.setEmail("cjparke@ncsu.edu");
        assertEquals("cjparke@ncsu.edu", testPerson.getEmail());
    }

    /**
     * Tests the getter and setter methods for the phone number.
     */
    @Test
    public void testGetAndSetPhone() {
        assertEquals("3363440576", testPerson.getPhone());
        testPerson.setPhone("1234567890");
        assertEquals("1234567890", testPerson.getPhone());
    }

    /**
     * Tests the getter for the template variables mapping
     */
    @Test
    public void testGetTemplateVariables() {
        Map<String, String> map = new HashMap<>();
        map.put("person" + ".name", "Will" + " " + "Duke");
        map.put("person" + ".first_name", "Will");
        map.put("person" + ".last_name", "Duke");
        assertEquals(map, testPerson.getTemplateVariables());
    }

    /**
     * Tests the getter for the template variable descriptions 
     */
    @Test
    public void testGetTemplateVariableDescriptions() {
        Map<String, String> map = new HashMap<>();
        map.put("${" + "person" + ".name" + "}", "Email/Text Recipient's Full Name");
        map.put("${" + "person" + ".first_name" + "}", "Email/Text Recipient's First Name");
        map.put("${" + "person" + ".last_name" + "}", "Email/Text Recipient's Last Name");
        assertEquals(map, testPerson.getTemplateVariableDescriptions());
    }

}
