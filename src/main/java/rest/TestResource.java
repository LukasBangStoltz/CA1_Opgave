package rest;

import DTO.MemberDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.MemberFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Test Web Service
 *
 * @author lam
 */
@Path("test")
public class TestResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final MemberFacade FACADE = MemberFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TestResource
     */
    public TestResource() {
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        /*
        System.out.println("DEPLOYED       -->"+System.getenv("DEPLOYED"));
        System.out.println("USER           -->"+System.getenv("USER"));
        System.out.println("PW             -->"+System.getenv("PW"));
        System.out.println("CONNECTION_STR -->"+System.getenv("CONNECTION_STR"));
         */
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("/name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByName(@PathParam("name") String name) {
        MemberDTO member = FACADE.getMemberByName(name);
        return GSON.toJson(member);
    }

    @Path("/count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCountMovies() {
        long count = (long) FACADE.countAllMovies();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }
}
