package facades;

import DTO.MemberDTO;
import entities.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

    public List<MemberDTO> getMemberByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Member.getByName");
            query.setParameter("name", name);
            List<MemberDTO> memberList = query.getResultList();
            return memberList;
        } finally {
            em.close();
        }
    }

}
