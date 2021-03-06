package rest;

import DTO.JokeDTO;
import entities.Joke;
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
public class JokeResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Joke j1, j2, j3, j4, j5, j6, j7, j8, j9, j10;

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
        Joke j1 = new Joke("Hvad spiser koen, lol ihvertfald ikke mælk", "Dyrejoke");
        Joke j2 = new Joke("Hvad spiser fåret? uld", "Dyrejoke");
        Joke j3 = new Joke("Hvad spiser asiateren? hunde", "Racejoke");
        j4 = new Joke("Hvad spiser asiateren? 4", "Racejoke");
        j5 = new Joke("Hvad spiser asiateren? 5", "Racejoke");
        j6 = new Joke("Hvad spiser asiateren? 6", "Racejoke");
        j7 = new Joke("Hvad spiser asiateren? 7", "Racejoke");
        j8 = new Joke("Hvad spiser asiateren? 8", "Racejoke");
        j9 = new Joke("Hvad spiser asiateren? 9", "Racejoke");
        j10 = new Joke("Hvad spiser asiateren? 10", "Racejoke");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.createNativeQuery("alter table JOKE AUTO_INCREMENT = 1").executeUpdate();
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            em.persist(j4);
            em.persist(j5);
            em.persist(j6);
            em.persist(j7);
            em.persist(j8);
            em.persist(j9);
            em.persist(j10);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/jokes").then().statusCode(200);
    }

    //This test assumes the database contains two rows
    @Test
    public void testDummyMsg() throws Exception {
        given()
                .contentType("application/json")
                .get("/jokes/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Hello World"));
    }

    @Test
    public void testGetAllJokes() throws Exception {
        given()
                .contentType("application/json")
                .get("/jokes/alljokes").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("size()", is(10));
    }

    @Test
    public void testGetJokeByType() throws Exception {
        given()
                .contentType("application/json")
                .get("/jokes/type/dyrejoke").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("size()", is(2));
    }

    @Test
    public void testGetJokeById() throws Exception {
        given()
                .contentType("application/json")
                .get("/jokes/id/1").then()
                .assertThat()
                .body("id", equalTo(1));
    }

    // Vi ved ikke hvordan denne test skal testes
    /*
    @Test
    public void testGetRandomJoke() throws Exception {
        given()
                .contentType("application/json")
                .get("/jokes/randomjoke").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body();
    }
     */
}
