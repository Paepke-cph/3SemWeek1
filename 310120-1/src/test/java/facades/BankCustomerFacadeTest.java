/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.BankCustomer;
import entities.dto.BankCustomerDTO;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author thomas
 */
public class BankCustomerFacadeTest {
    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final BankCustomerFacade FE = BankCustomerFacade.getInstance(ENF);
    public BankCustomerFacadeTest() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager entityManager = ENF.createEntityManager();
        BankCustomer bc1 = new BankCustomer("Pedro","Nielsen","123131",
                231231.0, 10,"Bla Bla Bla");
        BankCustomer bc2 = new BankCustomer("Peter", "Larsen", "123194319",
                131411.0, 5, "Bla Bla Bla");
        entityManager.getTransaction().begin();
        entityManager.persist(bc1);
        entityManager.persist(bc2);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @AfterEach
    public void tearDown() {
        EntityManager entityManager = ENF.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("TRUNCATE TABLE BankCustomer").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Test
    public void testGetCustomerById_with_valid_id() {
        BankCustomerDTO dto = FE.getCustomerById(1);
        assertNotNull(dto);
        assertEquals(1, dto.getBankCustomerId());
    }
    @Test
    public void testGetCustomerById_with_invalid_id() {
        BankCustomerDTO dto = FE.getCustomerById(100000);
        assertNull(dto);
    }
    @Test
    public void testGetCustomerByName_with_valid_name() {
        List<BankCustomerDTO> dtos = FE.getCustomerByName("Pedro");
        assertEquals(1, dtos.size());
    }
    @Test
    public void testGetCustomerByName_with_invalid_name() {
        List<BankCustomerDTO> dtos = FE.getCustomerByName("This is really not a name");
        assertEquals(0, dtos.size());
    }
    @Test
    public void testAddCustomer_with_valid_customer() {
        BankCustomer bankCustomer = new BankCustomer("Peter", "Larsen", "4131312",
                141.0, 1, "Bla Bla Bla");
        FE.addCustomer(bankCustomer);
        assertTrue(bankCustomer.getId() > 0);
    }
    @Test
    public void testGetAllCustomers() {
        List<BankCustomer> bankCustomers = FE.getAllCustomers();
        assertEquals(2, bankCustomers.size());
    }
}
