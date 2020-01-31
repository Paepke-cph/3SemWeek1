package facades;

import entities.BankCustomer;
import entities.dto.BankCustomerDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private BankCustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomerFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public BankCustomerDTO getCustomerById(int id) {
        EntityManager entityManager = getEntityManager();
        BankCustomer bankCustomer = entityManager.find(BankCustomer.class,id);
        entityManager.close();
        if(bankCustomer == null) {
            return null;
        } else {
            return new BankCustomerDTO(bankCustomer);
        }
    }

    public List<BankCustomerDTO> getCustomerByName(String name) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<BankCustomer> query = entityManager
                .createQuery("SELECT bc FROM BankCustomer bc WHERE bc.firstName like :name",BankCustomer.class);
        query.setParameter("name",name);
        List<BankCustomer> bankCustomers = query.getResultList();
        return convertToDTOList(bankCustomers);
    }

    public BankCustomer addCustomer(BankCustomer bankCustomer) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(bankCustomer);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return bankCustomer;
    }

    public List<BankCustomer> getAllCustomers() {
        EntityManager entityManager = getEntityManager();
        List<BankCustomer> bankCustomers = entityManager.createQuery("SELECT bc FROM BankCustomer bc", BankCustomer.class)
                .getResultList();
        entityManager.close();
        return bankCustomers;
    }

    private List<BankCustomerDTO> convertToDTOList(List<BankCustomer> bankCustomers) {
        List<BankCustomerDTO> dtos = new ArrayList<>();
        bankCustomers.forEach((bankCustomer) -> {
            dtos.add(new BankCustomerDTO(bankCustomer));
        });
        return dtos;
    }
}
