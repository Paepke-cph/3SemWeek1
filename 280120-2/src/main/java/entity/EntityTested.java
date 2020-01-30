package entity;

import facade.CustomerFacade;
import java.util.List;

/**
 * @author benjaminp
 */
public class EntityTested {

    public static void main(String[] args) {
        CustomerFacade cf = new CustomerFacade();

        cf.addCustomer("Peter", "Larsen");
        cf.addCustomer("Holger", "Petersen");
        cf.addCustomer("Niels", "Larsen");

        List<Customer> customers = cf.getCustomers();
        customers.forEach(System.out::println);
        
        Customer c = cf.findById(1L);
        System.out.println(c);
        
        List<Customer> searchedCustomers = cf.findByLastName("Larsen");
        searchedCustomers.forEach(System.out::println);
        
        System.out.println("Customer count: " + cf.getNumberOfCustomers());
    }
}
