/**
 * Tests the appointment model. 
 * 
 * @author Will Duke
 */
package bco.scheduler.model;

import bco.scheduler.model.RSA;
import bco.scheduler.model.Technician;
import bco.scheduler.model.Assignment;
import bco.scheduler.model.FlooringType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Set;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;



public class AppointmentTest {

    CommunicationType communicationType = CommunicationType.EMAIL;
    CommunicationType communicationType2 = CommunicationType.TEXT;
    Customer customer = new Customer("Noah", "Trimble", "ntrimbl@ncsu.edu", "0987654321", communicationType, "123 St NC");
    Customer customer2 = new Customer("Soumya", "Bagade", "sbagade@ncsu.edu", "5432167890", communicationType2, "321 St NC");
    Technician tech = new Technician("Bob", "Dylan", "bob@gmail.com", "0192837465", true);
    Technician tech2 = new Technician("Jane", "Doe", "janedoe@gmail.com", "2143658709", false);
    Technician tech3 = new Technician("John", "Doe", "johndoe@gmail.com", "3216549870", false);
    Technician array[] = { tech };
    Technician array2[] = { tech2, tech3 };
    Set<Technician> technicians = new HashSet<Technician>(Arrays.asList(array));
    Set<Technician> technicians2 = new HashSet<Technician>(Arrays.asList(array2));
    FlooringType flooringtype = new FlooringType("hardwood", "", "", "BCO");
    FlooringType flooringtype2 = new FlooringType("tile", "", "", "BCO");
    Date startDateTime = new Date(115, 7, 29);
    LocalDate startTest = startDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    Date startDateTime2 = new Date(115, 7, 25);
    Date endDateTime = new Date(115, 8, 29);
    LocalDate endTest = endDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    Date endDateTime2 = new Date(115, 7, 26);



    /**
     * Tests that the Appointment object has been made through the constructor
     */
	@Test
	public void testConstructor() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer, flooringtype, startDateTime, endDateTime);
        assertNotNull(test);
    }

    /**
     * Tests the getter and setter methods for the id
     */
    @Test
    public void testGetAndSetId() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer, flooringtype, startDateTime, endDateTime);

        test.setId(1);
        assertEquals(1, test.getId());
    }

    /**
     * Tests the getter and setter methods for the technicians set
     */
    @Test
    public void testGetAndSetTechnicians() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer, flooringtype, startDateTime, endDateTime);
        test.assignAll(technicians);
        assertEquals(technicians, test.getTechnicians());
 
    }

    /**
     * Tests the getter and setter methods for the RSA
     */
    @Test
    public void testGetAndSetRSA() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer, flooringtype, startDateTime, endDateTime);

        final RSA rsa2 = new RSA();
        rsa2.setFirstName("Connor");
        rsa2.setLastName("Parke");
        rsa2.setEmail("cjparke@ncsu.edu");
        rsa2.setPhone("1234567890");

        assertEquals(rsa, test.getRSA());
        test.setRSA(rsa2);
        assertEquals(rsa2, test.getRSA());
    }

    /**
     * Tests the getter and setter methods for the customer
     */
    @Test
    public void testGetAndSetCustomer() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer,  flooringtype, startDateTime, endDateTime);

        assertEquals(customer, test.getCustomer());
        test.setCustomer(customer2);
        assertEquals(customer2, test.getCustomer());
    }

    /**
     * Tests the getter and setter methods for the start date time
     */
    @Test
    public void testGetAndSetStartDateTime() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer,  flooringtype, startDateTime, endDateTime);

        assertEquals(startDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), test.getStartDate());
        test.setStartDateTime(startDateTime2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        assertEquals(startDateTime2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), test.getStartDate());
    }

    /**
     * Tests the getter and setter methods for the end date time
     */
    @Test
    public void testGetAndSetEndDateTime() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer, flooringtype, startDateTime, endDateTime);

        assertEquals(endDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), test.getEndDate());
        test.setEndDateTime(endDateTime2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        assertEquals(endDateTime2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), test.getEndDate());
    }

    /**
     * Tests the getter and setter methods for the flooring type
     */
    @Test
    public void testGetAndSetFlooring() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer, flooringtype, startDateTime, endDateTime);
        test.setFlooring(flooringtype2);
        assertEquals(flooringtype2, test.getFlooring());
    }

    /**
     * Tests the getTemplateVariables method of the appointment class.
     */
    @Test
    public void testGetTemplateVariables() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer, flooringtype, startDateTime, endDateTime);
        test.assignAll(technicians);
        final Map<String, String> map = new HashMap<>();
        map.put("appointment" + ".start_date", test.getStartDate().format(DateTimeFormatter.ofPattern("EEEE MMMM dd")));
        map.put("appointment" + ".end_date", test.getEndDate().format(DateTimeFormatter.ofPattern("EEEE MMMM dd")));
        map.put("appointment" + ".customer_name",
                test.getCustomer().getFirstName() + " " + test.getCustomer().getLastName());
        map.put("appointment" + ".rsa_name", test.getRSA().getFirstName() + " " + test.getRSA().getLastName());
        map.put("appointment" + ".tech_names", "Bob Dylan");
        map.put("appointment" + ".flooring", "hardwood");
        assertEquals(map, test.getTemplateVariables());
    }

    /**
     * Tests the getTemplateVariableDescriptions method of the appointment class.
     */
    @Test
    public void testGetTemplateVariableDescriptions() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer, flooringtype, startDateTime, endDateTime);

        final Map<String, String> map = new HashMap<>();
        map.put("${" + "appointment" + ".start_date" + "}", "Appointment Start Date");
        map.put("${" + "appointment" + ".end_date" + "}", "Appointment End Date");
        map.put("${" + "appointment" + ".customer_name" + "}", "Appointment Customer Name");
        map.put("${" + "appointment" + ".rsa_name" + "}", "Appointment RSA Name");
        map.put("${" + "appointment" + ".tech_names" + "}", "Appointment Technician Names");
        map.put("${" + "appointment" + ".flooring" + "}", "Appointment Flooring Type");
        assertEquals(map, Appointment.getTemplateVariableDescriptions());
    }

    /**
     * Tests the getExtraDays method of the appointment class.
     */
    @Test
    public void testGetExtraDays() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer, flooringtype, startDateTime, endDateTime);
        assertEquals(31, Appointment.getExtraDays(test.getStartDate(), test.getEndDate()));
    }

    /**
     * Tests the getExtraDays method of the appointment class.
     */
    @Test
    public void testMoveDate() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer, flooringtype, startDateTime2, endDateTime2);
        test.moveDate(startTest, endTest);
        assertEquals(31, Appointment.getExtraDays(test.getStartDate(), test.getEndDate()));
    }

    /**
     * Tests the assignDay method of the appointment class.
     */
    @Test
    public void testAssignDay() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer,  flooringtype, startDateTime2, endDateTime2);
        test.assignDay(1, technicians);
        assertEquals(test.getAssignments().toArray().length, 2);
        assertEquals(((Assignment)test.getAssignments().toArray()[0]).getTechnicians(), technicians);
    }

    /**
     * Tests the assignAll method of the appointment class.
     */
    @Test
    public void testAssignAll() {
        final RSA rsa = new RSA();
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        final Appointment test = new Appointment(rsa, customer,  flooringtype, startDateTime2, endDateTime2);
        test.assignAll(technicians);
        
        assertEquals(((Assignment)test.getAssignments().toArray()[1]).getTechnicians(), technicians);
        assertEquals(((Assignment)test.getAssignments().toArray()[0]).getTechnicians(), technicians);
        assertEquals(test.getAssignments().toArray().length, 2);
    }

    /**
     * Tests the Assignment creation and methods
     */
    @Test
    public void testAssignments() {
        Assignment tester1 = new Assignment();
        Assignment tester2 = new Assignment(1);
        Assignment tester3 = new Assignment(1,technicians);

        assertEquals(tester1.getDayNumber(), 0);
        assertNull(tester1.getTechnicians());

        assertEquals(tester2.getDayNumber(),1);
        assertEquals(tester2.getTechnicians().size(), 0 );

        assertEquals(tester3.getDayNumber(), 1);
        assertEquals(tester3.getTechnicians(), technicians);
        
        
    }
	
}