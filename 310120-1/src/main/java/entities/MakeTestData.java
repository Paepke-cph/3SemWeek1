package entities;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MakeTestData {
    public static void main(String[] args) {
        BankCustomer bc1 = new BankCustomer("Pedro","Nielsen","123131",
                231231.0, 10,"Bla Bla Bla");
        BankCustomer bc2 = new BankCustomer("Peter", "Larsen", "123194319",
                131411.0, 5, "Bla Bla Bla");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(bc1);
        entityManager.persist(bc2);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
