/**
 * Tests the customer controller class.
 * 
 * @author Will Duke
 */
package bco.scheduler.controller;
import bco.scheduler.repository.CustomerRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.Test;
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

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private CustomerController customerController = new CustomerController();

    @Test
    public void getAllCustomersTest() throws Exception {
     
        Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");

        List<Customer> allCustomers = Arrays.asList(testCustomer);
 
        ResponseEntity<List<Customer>> re = ResponseEntity.ok(allCustomers);

        assertNull(customerController.getAllCustomers());

        when(customerController.getAllCustomers()).thenReturn(re);

        assertEquals(customerController.getAllCustomers(), re);
    }

    // @Test
    // public void getCustomerByIdTest() throws Exception {
     
    //     Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");

    //     List<Customer> allCustomers = Arrays.asList(testCustomer);
 
    //     given(customerController.getAllCustomers()).willReturn(allCustomers);
 
    //     mvc.perform(get("/customers/1")
    //         .contentType(MediaType.APPLICATION_JSON))
    //         .andExpect(status().isOk())
    //         .andExpect(jsonPath("$", hasSize(1)))
    //         .andExpect(jsonPath("$[0].name", is(testCustomer.getFirstName())));
    // }

    // @Test
    // public void createCustomerTest() throws Exception {
     
    //     Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");
 
    //     given(customerController.createCustomer(testCustomer)).willReturn(Status.isOk);
 
    //     mvc.perform(post("/customers")
    //         .contentType(MediaType.APPLICATION_JSON))
    //         .andExpect(status().isOk());
    // }

    // @Test
    // public void updateCustomerTest() throws Exception {
     
    //     Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");

    //     Customer testCustomerUpdated = new Customer("Jane", "Doe", "jdoe@gmail.com", "336-344-0576", CommunicationType.TEXT, "990 Oval Dr, Raleigh, NC 27606");
 
    //     given(customerController.updateCustomer(testCustomer.getId(), testCustomerUpdated)).willReturn(Status.isOk);
 
    //     mvc.perform(put("/customers/1")
    //         .contentType(MediaType.APPLICATION_JSON))
    //         .andExpect(status().isOk());
    // }

    // @Test
    // public void deleteCustomerTest() throws Exception {
     
    //     Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");

    //     List<Customer> allCustomers = Arrays.asList(testCustomer);
 
    //     given(customerController.deleteCustomer(1)).willReturn(Status.isOk);
 
    //     mvc.perform(delete("/customers/1")
    //         .contentType(MediaType.APPLICATION_JSON))
    //         .andExpect(status().isOk());
    // }

    // @Test
    // public void getCommunicationTypesTest() throws Exception {
     
    //     Customer testCustomer = new Customer("John", "Doe", "jdoe@gmail.com", "919-515-3000", CommunicationType.EMAIL_AND_TEXT, "890 Oval Dr, Raleigh, NC 27606");

    //     List<Customer> allCustomers = Arrays.asList(testCustomer);
 
    //     given(customerController.getCommunicationTypes()).willReturn(allCustomers);
 
    //     mvc.perform(get("/customers/communicationType")
    //         .contentType(MediaType.APPLICATION_JSON))
    //         .andExpect(status().isOk())
    //         .andExpect(jsonPath("$", hasSize(1)))
    //         .andExpect(jsonPath("$[0].name", is(testCustomer.getFirstName())));
    // }

}