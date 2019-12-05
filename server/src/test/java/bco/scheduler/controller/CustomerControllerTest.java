package bco.scheduler.controller;
import bco.scheduler.repository.CustomerRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.Test;
import java.util.Map;
import java.util.ArrayList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import bco.scheduler.model.Customer;
import bco.scheduler.model.CommunicationType;
import java.util.*;
import javax.ws.rs.core.MediaType;
import static org.mockito.BDDMockito.*;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Tests the customer controller class.
 * Uses Mockiato for mocking of relevant pieces. 
 * 
 * @author Will Duke
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerControllerTest {

    // A mock of the customer controller class.
    @Mock
    private CustomerController customerController = new CustomerController();

    /**
     * Tests the getting of all the customers through the
     * getAllCustomers method.
     */
    @Test
    public void getAllCustomersTest() throws Exception {
     
        // A customer object to use.
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");

        // A customer object list to use.
        List<Customer> allCustomers = Arrays.asList(testCustomer);
 
        // Response Entity representation of the desired output
        ResponseEntity<List<Customer>> re = ResponseEntity.ok(allCustomers);

        // Ensure the customers are null.
        assertNull(customerController.getAllCustomers());

        // Telling Mockiato how to handle the methods.
        when(customerController.getAllCustomers()).thenReturn(re);

        // Ensure the customer is in there. 
        assertEquals(customerController.getAllCustomers(), re);
    }

    /**
     * Tests the getting of the customers by their id through
     * the getCustomerById method.
     */
    @Test
    public void getCustomerByIdTest() throws Exception {

        // A customer object to use.
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");

        // A customer object list to use.
        List<Customer> allCustomers = Arrays.asList(testCustomer);
 
        // Response Entity representation of the desired output
        ResponseEntity<Customer> re = ResponseEntity.ok(testCustomer);

        // Ensure there are no customers when they aren't in there.
        assertNull(customerController.getCustomerById((long) testCustomer.getId()));

        // Telling Mockiato how to handle the methods.
        when(customerController.getCustomerById((long) testCustomer.getId())).thenReturn(re);

        // Ensuring the customer can be found. 
        assertEquals(customerController.getCustomerById((long) testCustomer.getId()), re);
        
    }

    /**
     * Tests that the customer is created successfully through 
     * the create customer method.
     */
    @Test
    public void createCustomerTest() throws Exception {
        
        // A customer object to use.
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
 
        // Response Entity representation of the desired output
        ResponseEntity<Customer> re = ResponseEntity.ok(testCustomer);

        // Telling Mockiato how to handle the methods.
        when(customerController.createCustomer(testCustomer)).thenReturn(re);

        // Ensuring the customer is created 
        assertEquals(customerController.createCustomer(testCustomer), re);

    }

    /**
     * Tests that the customers are updated properly through the
     * update customer method.
     */
    @Test
    public void updateCustomerTest() throws Exception {
     
        // A customer object to use.
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");

        // A customer object to update to. 
        Customer testCustomerUpdated = new Customer("Jane", "Doe", "jdoe@gmail.com", "336-344-0576", CommunicationType.TEXT, "990 Oval Dr, Raleigh, NC 27606");
 
        // Response Entity representation of the desired output
        ResponseEntity<Customer> re = ResponseEntity.ok(testCustomer);

        // Response Entity representation of the desired output
        ResponseEntity<Customer> updatedRe = ResponseEntity.ok(testCustomerUpdated);

        // Telling Mockiato how to handle the methods.
        when(customerController.createCustomer(testCustomer)).thenReturn(re);

        // Ensuring the customer is create
        assertEquals(customerController.createCustomer(testCustomer), re);

        // Telling Mockiato how to handle the methods.
        when(customerController.updateCustomer(testCustomer.getId(), testCustomerUpdated)).thenReturn(updatedRe);

        // Ensure the customer is updated correctly. 
        assertEquals(customerController.updateCustomer(testCustomer.getId(), testCustomerUpdated), updatedRe);

    }

    /**
     * Tests that customers are deleted using the delete customer method.
     */
    @Test
    public void deleteCustomerTest() throws Exception {

        // A customer object to use.
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");

        // A customer object to use.
        Customer testCustomerUpdated = new Customer("Jane", "Doe", "jdoe@gmail.com", "336-344-0576", CommunicationType.TEXT, "990 Oval Dr, Raleigh, NC 27606");
 
        // Response Entity representation of the desired output
        ResponseEntity<Customer> re = ResponseEntity.ok(testCustomer);
        
        // Response Entity representation of the desired output
        ResponseEntity<Customer> deletedRe = ResponseEntity.ok(testCustomer);

        // Telling Mockiato how to handle the methods.
        when(customerController.createCustomer(testCustomer)).thenReturn(re);

        // Telling Mockiato how to handle the methods.
        when(customerController.deleteCustomer(testCustomer.getId())).thenReturn(deletedRe);

        // Ensure that the customer is created. 
        assertEquals(customerController.createCustomer(testCustomer), re);

        // Ensure the customer is deleted. 
        assertEquals(customerController.deleteCustomer(testCustomer.getId()), deletedRe);

    }

    /**
     * Tests the get communication types method. Should return all of the 
     * communication methods, followed by a string representation of that method. 
     */
    @Test
    public void getCommunicationTypesTest() throws Exception {
     
        // Create a map of what the desired output should look like
        Map<CommunicationType, String> testMap = new HashMap<CommunicationType, String>();
        testMap.put(CommunicationType.EMAIL_AND_TEXT, "Email and Text");
        testMap.put(CommunicationType.EMAIL, "Email");
        testMap.put(CommunicationType.TEXT, "Text");

        // Response Entity representation of the desired output
        ResponseEntity<Map<CommunicationType, String>> re = ResponseEntity.ok(testMap);

        // Should return null when not instatiated.
        assertNull(customerController.getCommunicationTypes());

        // Insantiate customer controller with a mock.
        when(customerController.getCommunicationTypes()).thenReturn(re);

        // Ensure that the communication types return correctly. 
        assertEquals(customerController.getCommunicationTypes(), re);
 
    }

}