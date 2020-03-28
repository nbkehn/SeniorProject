package bco.scheduler.controller;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;
import bco.scheduler.model.FlooringType;
import java.util.*;
import java.util.ArrayList;
import bco.scheduler.model.CommunicationType;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;

/**
 * Tests the FlooringType controller class.
 * Uses Mockiato for mocking of relevant pieces. 
 * 
 * @author Will Duke
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class FlooringTypeControllerTest {

    // A mock of the flooringType controller class.
    @Mock
    private FlooringTypeController flooringTypeController = new FlooringTypeController();

    /**
     * Tests the getting of all the flooringTypes through the
     * getAllFlooringTypes method.
     */
    @Test
    public void getAllFlooringTypesTest() throws Exception {
     
        // An flooringType object to use.
        FlooringType testFlooringType = new FlooringType("hardwood", null, null, "BCO");

        // An flooringType object list to use.
        List<FlooringType> allFlooringTypes = Arrays.asList(testFlooringType);
 
        // Response Entity representation of the desired output
        ResponseEntity<List<FlooringType>> re = ResponseEntity.ok(allFlooringTypes);

        // Ensure the flooringTypes are null.
        assertNull(flooringTypeController.getAllFlooringTypes());

        // Telling Mockiato how to handle the methods.
        when(flooringTypeController.getAllFlooringTypes()).thenReturn(re);

        // Ensure the flooringType is in there. 
        assertEquals(flooringTypeController.getAllFlooringTypes(), re);
    }

    /**
     * Tests the getting of the flooringTypes by their id through
     * the getFlooringTypeById method.
     */
    @Test
    public void getFlooringTypeByIdTest() throws Exception {

        // A flooringType object to use.
        FlooringType testFlooringType = new FlooringType("hardwood", null, null, "BCO" );

        // A flooringType object list to use.
        List<FlooringType> allFlooringTypes = Arrays.asList(testFlooringType);
 
        // Response Entity representation of the desired output
        ResponseEntity<FlooringType> re = ResponseEntity.ok(testFlooringType);

        // Ensure there are no flooringTypes when they aren't in there.
        assertNull(flooringTypeController.getFlooringTypeById((long) testFlooringType.getId()));

        // Telling Mockiato how to handle the methods.
        when(flooringTypeController.getFlooringTypeById((long) testFlooringType.getId())).thenReturn(re);

        // Ensuring the flooringType can be found. 
        assertEquals(flooringTypeController.getFlooringTypeById((long) testFlooringType.getId()), re);
        
    }

    /**
     * Tests that the flooringType is created successfully through 
     * the create flooringType method.
     */
    @Test
    public void createFlooringTest() throws Exception {
        
        // A flooringType object to use.
        FlooringType testFlooringType = new FlooringType("hardwood", null, null, "BCO");

        // Response Entity representation of the desired output
        ResponseEntity<FlooringType> re = ResponseEntity.ok(testFlooringType);

        // Telling Mockiato how to handle the methods.
        when(flooringTypeController.createFlooring(testFlooringType)).thenReturn(re);

        // Ensuring the flooringType is created 
        assertEquals(flooringTypeController.createFlooring(testFlooringType), re);

    }

    /**
     * Tests that the flooringTypes are updated properly through the
     * update flooringType method.
     */
    @Test
    public void updateFlooringTest() throws Exception {
     
        // A flooringType object to use.
        FlooringType testFlooringType = new FlooringType("hardwood", null, null, "BCO");

        // A flooringType object to update to. 
        FlooringType testFlooringTypeUpdated = new FlooringType("hardwood", null, null, "BCO");

        // Response Entity representation of the desired output
        ResponseEntity<FlooringType> re = ResponseEntity.ok(testFlooringType);

        // Response Entity representation of the desired output
        ResponseEntity<FlooringType> updatedRe = ResponseEntity.ok(testFlooringTypeUpdated);

        // Telling Mockiato how to handle the methods.
        when(flooringTypeController.createFlooring(testFlooringType)).thenReturn(re);

        // Ensuring the flooringType is create
        assertEquals(flooringTypeController.createFlooring(testFlooringType), re);

        // Telling Mockiato how to handle the methods.
        when(flooringTypeController.updateFlooring(testFlooringType.getId(), testFlooringTypeUpdated)).thenReturn(updatedRe);

        // Ensure the flooringType is updated correctly. 
        assertEquals(flooringTypeController.updateFlooring(testFlooringType.getId(), testFlooringTypeUpdated), updatedRe);

    }

    /**
     * Tests that flooringTypes are deleted using the delete flooringType method.
     */
    @Test
    public void deleteFlooringTest() throws Exception {

        // A flooringType object to use.
        FlooringType testFlooringType = new FlooringType("hardwood", null, null, "BCO");

        // A flooringType object to update to. 
        FlooringType testFlooringTypeUpdated = new FlooringType("hardwood", null, null, "BCO");

        // Response Entity representation of the desired output
        ResponseEntity<FlooringType> re = ResponseEntity.ok(testFlooringType);
        
        // Response Entity representation of the desired output
        ResponseEntity<FlooringType> deletedRe = ResponseEntity.ok(testFlooringType);

        // Telling Mockiato how to handle the methods.
        when(flooringTypeController.createFlooring(testFlooringType)).thenReturn(re);

        // Telling Mockiato how to handle the methods.
        when(flooringTypeController.deleteFlooring(testFlooringType.getId())).thenReturn(deletedRe);

        // Ensure that the flooringType is created. 
        assertEquals(flooringTypeController.createFlooring(testFlooringType), re);

        // Ensure the flooringType is deleted. 
        assertEquals(flooringTypeController.deleteFlooring(testFlooringType.getId()), deletedRe);

    }

}
