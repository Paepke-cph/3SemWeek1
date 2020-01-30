package rest.service;

import entities.Employee;
import entities.dto.EmployeeDTO;
import facades.EmployeeFacade;

import javax.persistence.EntityManagerFactory;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/employee")
public class EmployeeResource {

    private EntityManagerFactory emf;
    private EmployeeFacade facade =  EmployeeFacade.getInstance(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllEmployees() {
        return Response.ok(facade.getAllEmployees()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") long id) {
        EmployeeDTO employee = facade.getEmployeeById(id);
        if(employee != null) {
            return Response.ok(employee).build();
        } else {
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeByName(@PathParam("name") String name) {
        return Response.ok(facade.getEmployeesByName(name)).build();
    }

    @GET
    @Path("/highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesWithHighestSalary() {
        return Response.ok(facade.getEmployeesWithHighestSalary()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmployee(List<EmployeeDTO> dtos) {
        dtos.forEach((dto) -> {
            Employee employee = new Employee(dto);
            facade.createEmployee(employee);
        });
        return Response.ok().build();
    }
}
