/**
 * Tests the CommunicationType Model class
 * 
 * @author Soumya Bagade
 */

package bco.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CommunicationTypeTest {

    /**
     * Tests that the CommunicationType object has been made through the constructor
     */
	@Test
	public void testConstructor() {
       CommunicationType test = CommunicationType.EMAIL;
       assertNotNull(test);
    }
 
    /**
     * Tests the getName() method
     */
	@Test
	public void testGetName() {
       // test email
       CommunicationType testEmail = CommunicationType.EMAIL;
       assertEquals(testEmail.getName(), "Email");

       // test text
       CommunicationType testText = CommunicationType.TEXT;
       assertEquals(testText.getName(), "Text");

       // test email and text
       CommunicationType testEmailAndText = CommunicationType.EMAIL_AND_TEXT;
       assertEquals(testEmailAndText.getName(), "Email and Text");

    }
}
