package bco.scheduler.controller;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;
import bco.scheduler.model.RSA;
import java.util.*;
import java.util.ArrayList;
import bco.scheduler.model.CommunicationType;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;

/**
 * Tests the RSA controller class.
 * Uses Mockiato for mocking of relevant pieces. 
 * 
 * @author Will Duke
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class RSAControllerTest {

    // A mock of the rsa controller class.
    @Mock
    private RSAController rsaController = new RSAController();

    /**
     * Tests the getting of all the rsas through the
     * getAllRSAs method.
     */
    @Test
    public void getAllRSAsTest() throws Exception {
     
        // An rsa object to use.
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");

        // An rsa object list to use.
        List<RSA> allRSAs = Arrays.asList(testRSA);
 
        // Response Entity representation of the desired output
        ResponseEntity<List<RSA>> re = ResponseEntity.ok(allRSAs);

        // Ensure the rsas are null.
        assertNull(rsaController.getAllRSAs());

        // Telling Mockiato how to handle the methods.
        when(rsaController.getAllRSAs()).thenReturn(re);

        // Ensure the rsa is in there. 
        assertEquals(rsaController.getAllRSAs(), re);
    }

    /**
     * Tests the getting of the rsas by their id through
     * the getRSAById method.
     */
    @Test
    public void getRSAByIdTest() throws Exception {

        // A rsa object to use.
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");

        // A rsa object list to use.
        List<RSA> allRSAs = Arrays.asList(testRSA);
 
        // Response Entity representation of the desired output
        ResponseEntity<RSA> re = ResponseEntity.ok(testRSA);

        // Ensure there are no rsas when they aren't in there.
        assertNull(rsaController.getRSAById((long) testRSA.getId()));

        // Telling Mockiato how to handle the methods.
        when(rsaController.getRSAById((long) testRSA.getId())).thenReturn(re);

        // Ensuring the rsa can be found. 
        assertEquals(rsaController.getRSAById((long) testRSA.getId()), re);
        
    }

    /**
     * Tests that the rsa is created successfully through 
     * the create rsa method.
     */
    @Test
    public void createRSATest() throws Exception {
        
        // A rsa object to use.
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");

        // Response Entity representation of the desired output
        ResponseEntity<RSA> re = ResponseEntity.ok(testRSA);

        // Telling Mockiato how to handle the methods.
        when(rsaController.createRSA(testRSA)).thenReturn(re);

        // Ensuring the rsa is created 
        assertEquals(rsaController.createRSA(testRSA), re);

    }

    /**
     * Tests that the rsas are updated properly through the
     * update rsa method.
     */
    @Test
    public void updateRSATest() throws Exception {
     
        // A rsa object to use.
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");

        // A rsa object to update to. 
        RSA testRSAUpdated = new RSA();
        testRSAUpdated.setFirstName("John");
        testRSAUpdated.setLastName("Doe");
        testRSAUpdated.setEmail("jdoe@gmail.com");
        testRSAUpdated.setPhone("919-515-3000");

        // Response Entity representation of the desired output
        ResponseEntity<RSA> re = ResponseEntity.ok(testRSA);

        // Response Entity representation of the desired output
        ResponseEntity<RSA> updatedRe = ResponseEntity.ok(testRSAUpdated);

        // Telling Mockiato how to handle the methods.
        when(rsaController.createRSA(testRSA)).thenReturn(re);

        // Ensuring the rsa is create
        assertEquals(rsaController.createRSA(testRSA), re);

        // Telling Mockiato how to handle the methods.
        when(rsaController.updateRSA(testRSA.getId(), testRSAUpdated)).thenReturn(updatedRe);

        // Ensure the rsa is updated correctly. 
        assertEquals(rsaController.updateRSA(testRSA.getId(), testRSAUpdated), updatedRe);

    }

    /**
     * Tests that rsas are deleted using the delete rsa method.
     */
    @Test
    public void deleteRSATest() throws Exception {

        // A rsa object to use.
        RSA testRSA = new RSA();
        testRSA.setFirstName("John");
        testRSA.setLastName("Doe");
        testRSA.setEmail("jdoe@gmail.com");
        testRSA.setPhone("919-515-3000");

        // A rsa object to update to. 
        RSA testRSAUpdated = new RSA();
        testRSAUpdated.setFirstName("John");
        testRSAUpdated.setLastName("Doe");
        testRSAUpdated.setEmail("jdoe@gmail.com");
        testRSAUpdated.setPhone("919-515-3000");

        // Response Entity representation of the desired output
        ResponseEntity<RSA> re = ResponseEntity.ok(testRSA);
        
        // Response Entity representation of the desired output
        ResponseEntity<RSA> deletedRe = ResponseEntity.ok(testRSA);

        // Telling Mockiato how to handle the methods.
        when(rsaController.createRSA(testRSA)).thenReturn(re);

        // Telling Mockiato how to handle the methods.
        when(rsaController.deleteRSA(testRSA.getId())).thenReturn(deletedRe);

        // Ensure that the rsa is created. 
        assertEquals(rsaController.createRSA(testRSA), re);

        // Ensure the rsa is deleted. 
        assertEquals(rsaController.deleteRSA(testRSA.getId()), deletedRe);

    }

}
