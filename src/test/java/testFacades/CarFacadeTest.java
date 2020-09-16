package testFacades;

import DTO.CarDTO;
import entities.Car;
import facades.CarFacade;
import utils.EMF_Creator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CarFacadeTest {

    private static EntityManagerFactory emf;
    private static CarFacade facade;

    Car car1;
    Car car2;

    public CarFacadeTest() {

    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CarFacade.getFacadeExample(emf);
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
        try {
            em.getTransaction().begin();
            em.createNativeQuery("DELETE FROM CAR").executeUpdate();
            em.createNativeQuery("alter table CAR AUTO_INCREMENT = 1").executeUpdate();

            car1 = new Car("Panamera", "Porsche", 2020, 100000, "Sumit");
            car2 = new Car("M5", "BMW", 2017, 50000, "Lukas");
            em.persist(car1);
            em.persist(car2);

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
    public void testgetAllCars() {

        List<CarDTO> listOfCars = facade.getAllCars();

        assertEquals(2, listOfCars.size(), "Expects the size of two");
        assertThat(listOfCars, everyItem(hasProperty("model")));
    }

    @Test
    public void testgetCarByModel() {

        List<CarDTO> car = facade.getByModel("Panamera");

        assertEquals(1, car.size());
    }

    @Test
    public void testgetCarByMake() {

        List<CarDTO> car = facade.getByMake("BMW");

        assertEquals(1, car.size());
    }

    @Test
    public void testgetCarByOwner() {

        CarDTO car = facade.getByOwner("Lukas");

        assertEquals("Lukas", car.getOwner());
    }

    @Test
    public void testgetCarByYear() {

        List<CarDTO> car = facade.getByYear(2020);

        assertEquals(1, car.size());
    }

    @Test
    public void testgetCarByPrice() {

        List<CarDTO> car = facade.getByPrice(50000);

        assertEquals(1, car.size());
    }

}
