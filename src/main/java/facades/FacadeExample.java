package facades;

import entities.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import DTO.MemberDTO;
import java.util.ArrayList;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeExample() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public List<MemberDTO> getAllMembers() {
        EntityManager em = emf.createEntityManager();
        List<Member> allMembers = null;
        List<MemberDTO> allMembersDTO = new ArrayList();
        
        try {
            allMembers = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();

            allMembers.forEach((Member member) -> {

                allMembersDTO.add(new MemberDTO(member));

            });

            return allMembersDTO;
        } finally {
            em.close();
        }
    }

}
