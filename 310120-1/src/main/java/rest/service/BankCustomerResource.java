package rest.service;

import com.google.gson.Gson;
import entities.BankCustomer;
import entities.dto.BankCustomerDTO;
import facades.BankCustomerFacade;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("bankcustomer")
public class BankCustomerResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    BankCustomerFacade facade =  BankCustomerFacade.getInstance(emf);

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllCustomers() {
        return Response.ok(facade.getAllCustomers()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") int id) {
        BankCustomerDTO dto = facade.getCustomerById(id);
        if(dto == null) {
            return Response.status(404).build();
        } else {
            return Response.ok(dto).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String entity) {
        BankCustomer bc = facade.addCustomer(new Gson().fromJson(entity,BankCustomer.class));
        return Response.ok(new BankCustomerDTO(bc)).build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(BankCustomer entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
