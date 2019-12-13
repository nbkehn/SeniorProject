package bco.scheduler.controller;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;
import bco.scheduler.model.Technician;
import java.util.*;
import java.util.ArrayList;
import bco.scheduler.model.CommunicationType;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;

/**
 * Tests the Technician controller class.
 * Uses Mockiato for mocking of relevant pieces. 
 * 
 * @author Will Duke
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class TechnicianControllerTest {

    // A mock of the technician controller class.
    @Mock
    private TechnicianController technicianController = new TechnicianController();

    /**
     * Tests the getting of all the technicians through the
     * getAllTechnicians method.
     */
    @Test
    public void getAllTechniciansTest() throws Exception {
     
        // An technician object to use.
        Technician testTechnician = new Technician("Bob", "Dylan", "bob@gmail.com", "0192837465", true);

        // An technician object list to use.
        List<Technician> allTechnicians = Arrays.asList(testTechnician);
 
        // Response Entity representation of the desired output
        ResponseEntity<List<Technician>> re = ResponseEntity.ok(allTechnicians);

        // Ensure the technicians are null.
        assertNull(technicianController.getAllTechnicians());

        // Telling Mockiato how to handle the methods.
        when(technicianController.getAllTechnicians()).thenReturn(re);

        // Ensure the technician is in there. 
        assertEquals(technicianController.getAllTechnicians(), re);
    }

    /**
     * Tests the getting of the technicians by their id through
     * the getTechnicianById method.
     */
    @Test
    public void getTechnicianByIdTest() throws Exception {

        // A technician object to use.
        Technician testTechnician = new Technician("Bob", "Dylan", "bob@gmail.com", "0192837465", true);

        // A technician object list to use.
        List<Technician> allTechnicians = Arrays.asList(testTechnician);
 
        // Response Entity representation of the desired output
        ResponseEntity<Technician> re = ResponseEntity.ok(testTechnician);

        // Ensure there are no technicians when they aren't in there.
        assertNull(technicianController.getTechnicianById((long) testTechnician.getId()));

        // Telling Mockiato how to handle the methods.
        when(technicianController.getTechnicianById((long) testTechnician.getId())).thenReturn(re);

        // Ensuring the technician can be found. 
        assertEquals(technicianController.getTechnicianById((long) testTechnician.getId()), re);
        
    }

    /**
     * Tests that the technician is created successfully through 
     * the create technician method.
     */
    @Test
    public void createTechnicianTest() throws Exception {
        
        Technician testTechnician = new Technician("Bob", "Dylan", "bob@gmail.com", "0192837465", true);

        // Response Entity representation of the desired output
        ResponseEntity<Technician> re = ResponseEntity.ok(testTechnician);

        // Telling Mockiato how to handle the methods.
        when(technicianController.createTechnician(testTechnician)).thenReturn(re);

        // Ensuring the technician is created 
        assertEquals(technicianController.createTechnician(testTechnician), re);

    }

    /**
     * Tests that the technicians are updated properly through the
     * update technician method.
     */
    @Test
    public void updateTechnicianTest() throws Exception {
     
        Technician testTechnician = new Technician("Bob", "Dylan", "bob@gmail.com", "0192837465", true);

        // A technician object to update to. 
        Technician testTechnicianUpdated = new Technician("Jane", "Doe", "janedoe@gmail.com", "2143658709", false);

        // Response Entity representation of the desired output
        ResponseEntity<Technician> re = ResponseEntity.ok(testTechnician);

        // Response Entity representation of the desired output
        ResponseEntity<Technician> updatedRe = ResponseEntity.ok(testTechnicianUpdated);

        // Telling Mockiato how to handle the methods.
        when(technicianController.createTechnician(testTechnician)).thenReturn(re);

        // Ensuring the technician is create
        assertEquals(technicianController.createTechnician(testTechnician), re);

        // Telling Mockiato how to handle the methods.
        when(technicianController.updateTechnician(testTechnician.getId(), testTechnicianUpdated)).thenReturn(updatedRe);

        // Ensure the technician is updated correctly. 
        assertEquals(technicianController.updateTechnician(testTechnician.getId(), testTechnicianUpdated), updatedRe);

    }

    /**
     * Tests that technicians are deleted using the delete technician method.
     */
    @Test
    public void deleteTechnicianTest() throws Exception {

        Technician testTechnician = new Technician("Bob", "Dylan", "bob@gmail.com", "0192837465", true);

        // A technician object to update to. 
        Technician testTechnicianUpdated = new Technician("Jane", "Doe", "janedoe@gmail.com", "2143658709", false);

        // Response Entity representation of the desired output
        ResponseEntity<Technician> re = ResponseEntity.ok(testTechnician);
        
        // Response Entity representation of the desired output
        ResponseEntity<Technician> deletedRe = ResponseEntity.ok(testTechnician);

        // Telling Mockiato how to handle the methods.
        when(technicianController.createTechnician(testTechnician)).thenReturn(re);

        // Telling Mockiato how to handle the methods.
        when(technicianController.deleteTechnician(testTechnician.getId())).thenReturn(deletedRe);

        // Ensure that the technician is created. 
        assertEquals(technicianController.createTechnician(testTechnician), re);

        // Ensure the technician is deleted. 
        assertEquals(technicianController.deleteTechnician(testTechnician.getId()), deletedRe);

    }

}
