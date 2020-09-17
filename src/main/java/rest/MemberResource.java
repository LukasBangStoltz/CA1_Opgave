package rest;

import DTO.MemberDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Member;
import utils.EMF_Creator;
import facades.MemberFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("groupmembers")
public class MemberResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final MemberFacade FACADE = MemberFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllGroupMembers() {

        List<MemberDTO> allMembersDTO = FACADE.getAllMembers();

        return GSON.toJson(allMembersDTO);  //Done manually so no need for a DTO
    }

    @GET
    @Path("/name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByName(@PathParam("name") String name) {
        MemberDTO member = FACADE.getMemberByName(name);
        return GSON.toJson(member);
    }

    @GET
    @Path("studentid/{studentId}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByName(@PathParam("studentId") int studentId) {
        MemberDTO member = FACADE.getMemberByStudentId(studentId);
        return GSON.toJson(member);
    }

    @Path("/count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCountMovies() {
        long count = (long) FACADE.countAllMembers();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

}
