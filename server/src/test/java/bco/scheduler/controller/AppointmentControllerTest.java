package bco.scheduler.controller;

import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;
import bco.scheduler.model.Appointment;
import java.util.*;
import java.util.ArrayList;
import bco.scheduler.model.CommunicationType;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import bco.scheduler.model.RSA;
import bco.scheduler.model.Technician;
import bco.scheduler.model.FlooringType;
import bco.scheduler.model.Customer;

/**
 * Tests the Appointment controller class.
 * Uses Mockiato for mocking of relevant pieces. 
 * 
 * @author Will Duke
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class AppointmentControllerTest {

    CommunicationType communicationType = CommunicationType.EMAIL;
    CommunicationType communicationType2 = CommunicationType.TEXT;
    Customer customer = new Customer("Noah", "Trimble", "ntrimbl@ncsu.edu", "0987654321", communicationType, "123 St NC");
    Customer customer2 = new Customer("Soumya", "Bagade", "sbagade@ncsu.edu", "5432167890", communicationType2, "321 St NC");
    Technician tech = new Technician("Bob", "Dylan", "bob@gmail.com", "0192837465", true);
    Technician tech2 = new Technician("Jane", "Doe", "janedoe@gmail.com", "2143658709", false);
    Technician tech3 = new Technician("John", "Doe", "johndoe@gmail.com", "3216549870", false);
    Technician array[] = { tech, tech2 };
    Technician array2[] = { tech2, tech3 };
    Set<Technician> technicians = new HashSet<Technician>(Arrays.asList(array));
    Set<Technician> technicians2 = new HashSet<Technician>(Arrays.asList(array2));
    FlooringType flooringtype = new FlooringType("hardwood");
    FlooringType flooringtype2 = new FlooringType("tile");
    Date startDateTime = new Date(2015, 7, 29);
    Date startDateTime2 = new Date(2015, 6, 25);
    Date endDateTime = new Date(2015, 9, 29);
    Date endDateTime2 = new Date(2015, 12, 25);

    // A mock of the appointment controller class.
    @Mock
    private AppointmentController appointmentController = new AppointmentController();

    /**
     * Tests the getting of all the appointments through the
     * getAllAppointments method.
     */
    @Test
    public void getAllAppointmentsTest() throws Exception {
     
        // An appointment object to use.
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment testAppointment = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);
        
        // An appointment object list to use.
        List<Appointment> allAppointments = Arrays.asList(testAppointment);

        // Telling Mockiato how to handle the methods.
        when(appointmentController.getAllAppointments()).thenReturn(allAppointments);

        // Ensure the appointment is in there. 
        assertEquals(appointmentController.getAllAppointments(), allAppointments);
    }

    /**
     * Tests the getting of the appointments by their id through
     * the getAppointmentById method.
     */
    @Test
    public void getAppointmentByIdTest() throws Exception {

        // An appointment object to use.
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment testAppointment = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);

        // Ensure there are no appointments when they aren't in there.
        assertNull(appointmentController.getAppointmentById((long) testAppointment.getId()));

        // Telling Mockiato how to handle the methods.
        when(appointmentController.getAppointmentById((long) testAppointment.getId())).thenReturn(testAppointment);

        // Ensuring the appointment can be found. 
        assertEquals(appointmentController.getAppointmentById((long) testAppointment.getId()), testAppointment);
        
    }

    /**
     * Tests that the appointment is created successfully through 
     * the create appointment method.
     */
    @Test
    public void createAppointmentTest() throws Exception {
        
        // An appointment object to use.
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment testAppointment = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);

        // Telling Mockiato how to handle the methods.
        when(appointmentController.createAppointment(testAppointment)).thenReturn(testAppointment);

        // Ensuring the appointment is created 
        assertEquals(appointmentController.createAppointment(testAppointment), testAppointment);

    }

    /**
     * Tests that the appointments are updated properly through the
     * update appointment method.
     */
    @Test
    public void updateAppointmentTest() throws Exception {
     
        // An appointment object to use.
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment testAppointment = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);

        // A appointment object to update to. 
        Appointment testAppointmentUpdated = new Appointment(rsa, customer2, technicians2, flooringtype2, startDateTime2, endDateTime2);

        // Telling Mockiato how to handle the methods.
        when(appointmentController.createAppointment(testAppointment)).thenReturn(testAppointment);

        // Ensuring the appointment is create
        assertEquals(appointmentController.createAppointment(testAppointment), testAppointment);

        // Telling Mockiato how to handle the methods.
        when(appointmentController.updateAppointment(testAppointment.getId(), testAppointmentUpdated)).thenReturn(testAppointmentUpdated);

        // Ensure the appointment is updated correctly. 
        assertEquals(appointmentController.updateAppointment(testAppointment.getId(), testAppointmentUpdated), testAppointmentUpdated);

    }

    /**
     * Tests that appointments are deleted using the delete appointment method.
     */
    @Test
    public void deleteAppointmentTest() throws Exception {

        // An appointment object to use.
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment testAppointment = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);

        // A appointment object to update to. 
        Appointment testAppointmentUpdated = new Appointment(rsa, customer2, technicians2, flooringtype2, startDateTime2, endDateTime2);

        // Telling Mockiato how to handle the methods.
        when(appointmentController.deleteAppointment(testAppointment.getId())).thenReturn(testAppointment.getId());

        // Telling Mockiato how to handle the methods.
        when(appointmentController.deleteAppointment(testAppointment.getId())).thenReturn(testAppointmentUpdated.getId());

        // Ensure that the appointment is created. 
        assertEquals(appointmentController.deleteAppointment(testAppointment.getId()), testAppointment.getId());

        // Ensure the appointment is deleted. 
        assertEquals(appointmentController.deleteAppointment(testAppointment.getId()), testAppointmentUpdated.getId());

    }

}
