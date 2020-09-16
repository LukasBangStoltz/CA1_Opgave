/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;


import DTO.JokeDTO;
import entities.Joke;
import utils.EMF_Creator;
import entities.Member;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.validation.constraints.AssertTrue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokeFacadeTest {
    
    private static EntityManagerFactory emf;
    private static JokeFacade facade;
    
    private Joke j1;
    private Joke j2;
    private Joke j3;

    public JokeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = JokeFacade.getJokeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        j1 = new Joke("Hvad spiser koen, lol ihvertfald ikke mælk", "Dyrejoke");
        j2 = new Joke("Hvad spiser fåret? uld", "Dyrejoke");
        j3 = new Joke("Hvad spiser asiateren? hunde", "Racejoke");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void getAllJokesTest() {
        List<JokeDTO> jokeList = facade.getAllJokes();
        int exp = 3;
        assertEquals(exp, jokeList.size(), "Expected thre rows from database");
    }
    
    @Test
    public void getJokeByIdTest(){
        JokeDTO joke = facade.getJokeById(j2.getId());
        assertEquals("Hvad spiser fåret? uld", joke.getTheJoke());
    }
    
    @Test
    public void getJokeByTypeTest(){
        List <JokeDTO> jokeTypeList = facade.getJokeByType("Dyrejoke");
        int exp = 2;
        assertEquals(exp, jokeTypeList.size());
    }
    
    @Test
    public void getRandomJokeTest(){
        Random random = new Random();
        JokeDTO joke = facade.getRandomJoke();
      
        long jokeId = joke.getId();
        
        assertTrue(jokeId >= 1 || jokeId <= 3);
    }


    
}
