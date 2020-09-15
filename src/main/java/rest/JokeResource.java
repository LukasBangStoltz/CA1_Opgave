package rest;

import DTO.JokeDTO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.MemberFacade;
import facades.JokeFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("jokes")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final JokeFacade FACADE = JokeFacade.getJokeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("alljokes")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes() {
        List<JokeDTO> allJokeDTOs = FACADE.getAllJokes();
        return GSON.toJson(allJokeDTOs);
    }

    @Path("id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJokeById(@PathParam("id") Long id) {

        JokeDTO joke = FACADE.getJokeById(id);

        return GSON.toJson(joke);
    }

    @Path("type/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJokeById(@PathParam("type") String jokeType) {

        List<JokeDTO> jokeDTOlist = FACADE.getJokeByType(jokeType);
        return GSON.toJson(jokeDTOlist);
    }
}
