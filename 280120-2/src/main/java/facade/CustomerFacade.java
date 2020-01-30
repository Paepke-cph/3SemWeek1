package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author benjaminp
 */
public class CustomerFacade {
    private EntityManagerFactory emf;
    public EntityManager getManager() { return emf.createEntityManager(); }

    public CustomerFacade() {
        emf = Persistence.createEntityManagerFactory("pu");
    }

    /**
     * Find a Customer using the given ID.
     * @param id The id of the customer.
     * @return The Customer object, if one is found - NULL if no customer is found.
     */
    public Customer findById(Long id) {
        EntityManager em = getManager();
        Customer customer = em.find(Customer.class, id);
        em.close();
        return customer;
    }

    /**
     * Gets the total number of Customers currently stored.
     * @return The number of customers.
     */
    public int getNumberOfCustomers() {
        EntityManager em = getManager();
        int count = 0;
        try {
            count = Integer.parseInt((em.createQuery("SELECT count(c) FROM Customer c").getSingleResult()).toString());
        } catch (Exception e ) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return count;
    }

    /**
     * Finds all Customers with a last name similar to the one provided.
     * @param lastName The last name to be searched for.
     * @return A list of customers found with a matching last name.
     */
    public List<Customer> findByLastName(String lastName) {
        EntityManager em = getManager();
        TypedQuery query =em.createQuery("SELECT c FROM Customer c where c.lastName LIKE ?1", Customer.class).setParameter(1, lastName);
        List<Customer> customers = query.getResultList();
        em.close();
        return customers;
    }

    /**
     * Gets all the Customers currently stored.
     * @return List of all the customers.
     */
    public List<Customer> getCustomers() {
        EntityManager em = getManager();   
        TypedQuery q = em.createQuery("SELECT c FROM Customer c", Customer.class);
        List<Customer> customers = q.getResultList();
        em.close();
        return customers;
    }

    /**
     * Stores a customers.
     * @param firstName The first name of the Customer.
     * @param lastName The last name of the Customer.
     * @return The generated Customer object.
     */
    public Customer addCustomer(String firstName, String lastName) {
        EntityManager em = getManager();
        Customer customer = new Customer(firstName,lastName);
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return customer;
    }
    
    public void persist(Customer customer) {
        EntityManager em = getManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    public void close() {
        emf.close();
    }
}
