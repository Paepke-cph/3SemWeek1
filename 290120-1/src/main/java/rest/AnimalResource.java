package rest;

import dto.AnimalDTO;
import com.google.gson.Gson;
import entity.Animal;
import entity.AnimalSound;
import entity.AnimalType;
import facade.AnimalFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/animal")
public class AnimalResource {
    private AnimalFacade animalFacade = new AnimalFacade();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAnimals() {
        return Response.ok(animalFacade.getAllAnimals()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putAnimal(AnimalDTO animalDTO) {
        if(animalDTO.name != null && animalDTO.age != null) {
            System.out.println(animalDTO.toString());
            animalFacade.addAnimal(new Animal(animalDTO));
            return Response.ok().build();
        } else {
            return Response.status(400).build();
        }
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandom() {
        return Response.ok(animalFacade.getRandomAnimal()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnimalById(@PathParam("id") int id) {
        Animal animal = animalFacade.findById(id);
        if(animal != null) {
            return Response.ok(animal).build();
        } else {
            return Response.status(404).build();
        }
    }
}
