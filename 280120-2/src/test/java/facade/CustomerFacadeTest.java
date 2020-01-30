package facade;

import entity.Customer;
import org.junit.jupiter.api.*;

import javax.persistence.Persistence;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author benjaminp
 */
public class CustomerFacadeTest {

    private CustomerFacade customerFacade;
    public CustomerFacadeTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        customerFacade = new CustomerFacade();
    }

    @Test
    public void testAddCustomer() {
        customerFacade.addCustomer("Lars","Larsen");
        assertEquals(1, customerFacade.getNumberOfCustomers());
    }
    @Test
    public void testAddCustomer_multiple_customers() {
        customerFacade.addCustomer("Lars","Larsen");
        customerFacade.addCustomer("Lars","Larsen");
        customerFacade.addCustomer("Lars","Larsen");
        assertEquals(3, customerFacade.getNumberOfCustomers());
    }
    @Test
    public void testFindById_with_valid_id() {
        customerFacade.addCustomer("Lars","Larsen");
        assertEquals(1, customerFacade.getNumberOfCustomers());
        assertEquals(1L, customerFacade.findById(1L).getId());
    }
    @Test
    public void testFindById_with_invalid_id(){
        assertNull(customerFacade.findById(100L));
    }
    @Test
    public void testFindByLastName_with_valid_last_name() {
        String searchTerm = "Larsen";
        customerFacade.addCustomer("Lars", "Larsen");
        List<Customer> customers = customerFacade.findByLastName(searchTerm);
        assertEquals(1, customers.size());
        assertEquals("Lars",customers.get(0).getFirstName());
        assertEquals("Larsen",customers.get(0).getLastName());
    }
    @Test
    public void testFindByLastName_with_invalid_last_name() {
        String searchTerm = "This is not really a last name";
        List<Customer> customers = customerFacade.findByLastName(searchTerm);
        assertEquals(0, customers.size());
    }
}
