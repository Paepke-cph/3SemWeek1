package facades;

import entities.Employee;
import entities.dto.EmployeeDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory entityManagerFactory;
    
    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }
    /**
     * Gets the single instance of the Facade
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new EmployeeFacade();
            entityManagerFactory = emf;
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Gets an Employee by the given ID, null if none were found.
     * @param id the id to search for.
     * @return The Employee object.
     */
    public EmployeeDTO getEmployeeById(Long id){
        EntityManager entityManager = getEntityManager();
        EmployeeDTO dto = null;
        try {
            dto = new EmployeeDTO(entityManager.find(Employee.class, id));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return dto;
    }

    /**
     * Gets a list of employees matching a given name.
     * @param name The name of the employee
     * @return A list of employees.
     */
    public List<EmployeeDTO> getEmployeesByName(String name) {
        EntityManager entityManager = getEntityManager();
        List<Employee> employees = new ArrayList<>();
        try {
            employees = entityManager.createQuery("SELECT e FROM Employee e WHERE e.name LIKE :name",Employee.class)
                    .setParameter("name",name)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return convertToDTOList(employees);
    }

    /**
     * Gets all the employees
     * @return a list of all employees.
     */
    public List<EmployeeDTO> getAllEmployees() {
        EntityManager entityManager = getEntityManager();
        List<Employee> employees = new ArrayList<>();
        try {
            employees = entityManager.createQuery("SELECT e FROM Employee e", Employee.class)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return convertToDTOList(employees);
    }

    public List<EmployeeDTO> getEmployeesWithHighestSalary() {
        EntityManager entityManager = getEntityManager();
        List<Employee> employees = new ArrayList<>();
        try {
            employees = entityManager.createQuery("SELECT e FROM Employee e WHERE e.salary = " +
                    "(SELECT max(e.salary) FROM Employee e)", Employee.class)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return convertToDTOList(employees);
    }

    /**
     * Persists a object of type Employee.
     * @param employee The employee to be persisted.
     */
    public Employee createEmployee(Employee employee) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(employee);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return employee;
    }

    private List<EmployeeDTO> convertToDTOList(List<Employee> employees) {
        List<EmployeeDTO> dtos = new ArrayList<>();
        employees.forEach((employee) -> {dtos.add(new EmployeeDTO(employee));});
        return dtos;
    }
}
