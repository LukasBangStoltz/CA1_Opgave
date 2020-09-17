package rest;

import DTO.CarDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.CarFacade;
import java.util.List;
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
@Path("car")
public class CarResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final CarFacade FACADE = CarFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String allCars() {

        List<CarDTO> cars = FACADE.getAllCars();

        return GSON.toJson(cars);
    }

    @GET
    @Path("/make/{make}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarByMake(@PathParam("make") String make) {
        List<CarDTO> cars = FACADE.getByMake(make);
        return GSON.toJson(cars);
    }

    @GET
    @Path("/price/{price}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarByPrice(@PathParam("price") int price) {
        List<CarDTO> cars = FACADE.getByPrice(price);
        return GSON.toJson(cars);
    }

    @GET
    @Path("/model/{model}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarByModel(@PathParam("model") String model) {
        List<CarDTO> cars = FACADE.getByModel(model);
        return GSON.toJson(cars);
    }

    @GET
    @Path("/year/{year}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarByYear(@PathParam("year") int year) {
        List<CarDTO> cars = FACADE.getByYear(year);
        return GSON.toJson(cars);
    }
}
