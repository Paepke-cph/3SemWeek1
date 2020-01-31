package facades;

import entities.Employee;
import entities.dto.EmployeeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeFacadeTest {
    private EmployeeFacade employeeFacade;

    @BeforeEach
    public void setUpClass() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        // We are doing this over two transaction to make sure Peter is the first to be placed in the database.
        entityManager.persist(new Employee("Peter","Copenhagen",1));
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(new Employee("Lotte","Copenhagen",100014));
        entityManager.persist(new Employee("Niels","Copenhagen",1000005));
        entityManager.persist(new Employee("Kasper","Copenhagen",1004));
        entityManager.getTransaction().commit();
        entityManager.close();
        employeeFacade = EmployeeFacade.getInstance(entityManagerFactory);
    }

    @Test
    public void testGetEmployeeById_with_valid_id() {
        EmployeeDTO employee = employeeFacade.getEmployeeById(1L);
        assertEquals("Peter", employee.name);
        assertEquals("Copenhagen", employee.address);
    }

    @Test
    public void testGetEmployeeById_with_invalid_id() {
        EmployeeDTO employeeDTO = employeeFacade.getEmployeeById(1000L);
        assertNull(employeeDTO);
    }

    @Test
    public void testGetEmployeeByName_with_valid_name() {
        List<EmployeeDTO> employees = employeeFacade.getEmployeesByName("Niels");
        EmployeeDTO employee = employees.get(0);
        assertEquals(1, employees.size());
        assertEquals("Niels", employee.name);
        assertEquals("Copenhagen", employee.address);
    }

    @Test
    public void testGetEmployeeByName_with_invalid_name() {
        List<EmployeeDTO> employees = employeeFacade.getEmployeesByName("This should not be a name.");
        assertEquals(0, employees.size());
    }

    @Test
    public void testGetAllEmployees() {
        List<EmployeeDTO> employeeDTOS = employeeFacade.getAllEmployees();
        assertEquals(4,employeeDTOS.size());
    }

    @Test
    public void testGetEmployeesWithHighestSalary() {
        List<EmployeeDTO> employeeDTOS = employeeFacade.getEmployeesWithHighestSalary();
        EmployeeDTO employeeDTO = employeeDTOS.get(0);
        assertEquals(1, employeeDTOS.size());
        assertEquals("Niels",employeeDTO.name);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee("Kevin", "Lyngby", 10000000);
        employeeFacade.createEmployee(employee);
        assertNotNull(employee.getId());
    }
}