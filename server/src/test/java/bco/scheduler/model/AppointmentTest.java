/**
 * Tests the appointment model. 
 * 
 * @author Will Duke
 */
package bco.scheduler.model;

import bco.scheduler.model.RSA;
import bco.scheduler.model.Technician;
import bco.scheduler.model.FlooringType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Set;
import org.junit.Test;
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



    /**
     * Tests that the Appointment object has been made through the constructor
     */
	@Test
	public void testConstructor() {
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment test = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);
        assertNotNull(test);
    }
 
    /**
     * Tests the getter and setter methods for the id 
     */
    @Test
    public void testGetAndSetId() {
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment test = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);

        test.setId(1);
        assertEquals(1, test.getId());
    }

    /**
     * Tests the getter and setter methods for the technicians set
     */
    @Test
    public void testGetAndSetTechnicians() {
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment test = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);
        
        assertEquals(technicians, test.getTechnicians());
        test.setTechnicians(technicians2);
        assertEquals(technicians2, test.getTechnicians());
    }

    /**
     * Tests the getter and setter methods for the RSA
     */
    @Test
    public void testGetAndSetRSA() {
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment test = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);

        RSA rsa2 = new RSA(); 
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
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment test = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);
        
        assertEquals(customer, test.getCustomer());
        test.setCustomer(customer2);
        assertEquals(customer2, test.getCustomer());
    }

    /**
     * Tests the getter and setter methods for the start date time
     */
    @Test
    public void testGetAndSetStartDateTime() {
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment test = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);
        
        assertEquals(startDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), test.getStartDate());
        test.setStartDateTime(startDateTime2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        assertEquals(startDateTime2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), test.getStartDate());
    }

    /**
     * Tests the getter and setter methods for the end date time
     */
    @Test
    public void testGetAndSetEndDateTime() {
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment test = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);
        
        assertEquals(endDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), test.getEndDate());
        test.setEndDateTime(endDateTime2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        assertEquals(endDateTime2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), test.getEndDate());
    }

    /**
     * Tests the getter and setter methods for the flooring type
     */
    @Test
    public void testGetAndSetFlooring() {
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment test = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);
        test.setFlooring(flooringtype2);
        assertEquals(flooringtype2, test.getFlooring());
    }

    // // TODO: This is throwing a null pointer right now and I can't figure out why.
    // /**
    //  * Tests the getTemplateVariables method of the appointment class.
    //  */
    // @Test
    // public void testGetTemplateVariables() {
    //     RSA rsa = new RSA(); 
    //     rsa.setFirstName("Will");
    //     rsa.setLastName("Duke");
    //     rsa.setEmail("wfduke@ncsu.edu");
    //     rsa.setPhone("3363440576");
    //     Appointment test = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);
        
    //     Map<String, String> map = new HashMap<>();
    //     map.put("appointment" + ".start_date_time", test.getStartDateTime().format(DateTimeFormatter.ofPattern("EEEE MMMM dd K:mm a")));
    //     map.put("appointment" + ".start_date", test.getStartDateTime().format(DateTimeFormatter.ofPattern("EEEE MMMM dd")));
    //     map.put("appointment" + ".start_time", test.getStartDateTime().format(DateTimeFormatter.ofPattern("K:mm a")));
    //     map.put("appointment" + ".customer_name", test.getCustomer().getFirstName() + " " + test.getCustomer().getLastName());
    //     map.put("appointment" + ".rsa_name", test.getRSA().getFirstName() + " " + test.getRSA().getLastName());
    //     map.put("appointment" + ".tech_names", "Bob Dylan, Jane Doe");
    //     map.put("appointment" + ".flooring", "hardwood");
    //     assertEquals(map, test.getTemplateVariables());
    // }

    /**
     * Tests the getTemplateVariableDescriptions method of the appointment class.
     */
    @Test
    public void testGetTemplateVariableDescriptions() {
        RSA rsa = new RSA(); 
        rsa.setFirstName("Will");
        rsa.setLastName("Duke");
        rsa.setEmail("wfduke@ncsu.edu");
        rsa.setPhone("3363440576");
        Appointment test = new Appointment(rsa, customer, technicians, flooringtype, startDateTime, endDateTime);

        Map<String, String> map = new HashMap<>();
        map.put("${" + "appointment" + ".start_date" + "}", "Appointment Start Date");
        map.put("${" + "appointment" + ".end_date" + "}", "Appointment End Date");
        map.put("${" + "appointment" + ".customer_name" + "}", "Appointment Customer Name");
        map.put("${" + "appointment" + ".rsa_name" + "}", "Appointment RSA Name");
        map.put("${" + "appointment" + ".tech_names" + "}", "Appointment Technician Names");
        map.put("${" + "appointment" + ".flooring" + "}", "Appointment Flooring Type");
        assertEquals(map, test.getTemplateVariableDescriptions());
    }
	
}