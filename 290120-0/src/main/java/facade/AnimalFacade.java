package facade;

import entity.Animal;
import entity.AnimalSound;
import entity.AnimalType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Random;

public class AnimalFacade {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
    public EntityManager getManager() { return entityManagerFactory.createEntityManager(); }

    public Animal getRandomAnimal() {
        EntityManager entityManager = getManager();
        Animal animal = null;
        try {
            int max = entityManager.createQuery("SELECT max(a.id) FROM Animal a", Integer.class).getSingleResult();
            int randomId = new Random().nextInt(max+1);
            animal = entityManager.find(Animal.class, randomId);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return animal;
    }

    public void addAnimal(Animal animal) {
        EntityManager entityManager = getManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(animal);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Animal findById(Integer id) {
        EntityManager entityManager = getManager();
        Animal animal = null;
        try {
            animal = entityManager.find(Animal.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return animal;
    }

    public static void main(String[] args) {
        AnimalFacade animalFacade = new AnimalFacade();
        animalFacade.addAnimal(new Animal("Fido",10, AnimalSound.Bark, AnimalType.Dog));
        animalFacade.addAnimal(new Animal("Pedro",1, AnimalSound.Quack, AnimalType.Duck));
    }

    public List<Animal> getAllAnimals() {
        EntityManager entityManager = getManager();
        List<Animal> animals = null;
        try {
            animals = entityManager.createQuery("SELECT a FROM Animal a",Animal.class).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return animals;
    }
}
