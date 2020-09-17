package rest;

import entities.Car;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test

//@Disabled
public class CarResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Car car1, car2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() throws InterruptedException {
        EntityManager em = emf.createEntityManager();
        car1 = new Car("Panamera", "Porsche", 2020, 100000, "Sumit");
        car2 = new Car("M5", "BMW", 2017, 50000, "Lukas");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.createNativeQuery("alter table CAR AUTO_INCREMENT = 1").executeUpdate();
            em.persist(car1);
            em.persist(car2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/car").then().statusCode(200);
    }

    //This test assumes the database contains two rows
    @Test
    public void testDummyMsg() throws Exception {
        given()
                .contentType("application/json")
                .get("/car/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Hello World"));
    }

    @Test
    public void testGetAllCars() throws Exception {
        given()
                .contentType("application/json")
                .get("/car/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("size()", is(2));
    }

    @Test
    public void testGetCarByMake() throws Exception {
        given()
                .contentType("application/json")
                .get("/car/make/{make}", car1.getMake()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("make", hasItems("Porsche"));
    }

    @Test
    public void testGetCarByPrice() throws Exception {
        given()
                .contentType("application/json")
                .get("/car/price/{price}", car2.getPrice()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("price", hasItems(50000));
    }
    
    @Test
    public void testGetCarByModel() throws Exception {
        given()
                .contentType("application/json")
                .get("/car/model/{model}", car2.getModel()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("model", hasItems("M5"));
    }
    
    @Test
    public void testGetCarByYear() throws Exception {
        given()
                .contentType("application/json")
                .get("/car/year/{year}", car2.getYear()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("year", hasItems(2017));
    }
}
