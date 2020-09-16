package testFacades;

import DTO.MemberDTO;
import utils.EMF_Creator;
import java.util.List;
import entities.Member;
import facades.MemberFacade;
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
public class MemberFacadeTest {

    private static EntityManagerFactory emf;
    private static MemberFacade facade;

    public MemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MemberFacade.getFacadeExample(emf);
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
            em.createNativeQuery("DELETE FROM MEMBER").executeUpdate();
            em.persist(new Member("Jon", 235, "Prison Break"));
            em.persist(new Member("Lars", 132, "Hannah Montana"));

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
    public void testgetAllMembers() {

        List<MemberDTO> listOfMembers = facade.getAllMembers();

        assertEquals(2, listOfMembers.size(), "Expects the size of two");
        assertThat(listOfMembers, everyItem(hasProperty("favoriteSeries")));
    }

    @Test
    public void testgetMemberByName() {

        MemberDTO member = facade.getMemberByName("Lars");

        assertEquals("Hannah Montana", member.getFavoriteSeries());
    }

    @Test
    public void testgetMemberByStudentId() {

        MemberDTO member = facade.getMemberByStudentId(235);

        assertEquals(235, member.getStudentId());
    }

    @Test
    public void testCountMembers() {

        long count = facade.countAllMembers();

        assertEquals(2, count);
    }

}
