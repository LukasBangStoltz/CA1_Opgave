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
public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MemberFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
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
            allMembers = em.createNamedQuery("Member.getAll", Member.class).getResultList();

            allMembers.forEach((Member member) -> {

                allMembersDTO.add(new MemberDTO(member));

            });

            return allMembersDTO;
        } finally {
            em.close();
        }

    }

    public MemberDTO getMemberByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Member.getByName", Member.class);
            query.setParameter("name", name);
            Member member = (Member) query.getSingleResult();
            return new MemberDTO(member);
        } finally {
            em.close();
        }
    }

    public MemberDTO getMemberByStudentId(int studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Member.getByStudentId", Member.class);
            query.setParameter("studentId", studentId);
            Member member = (Member) query.getSingleResult();
            return new MemberDTO(member);
        } finally {
            em.close();
        }
    }

    public long countAllMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            long count = (long) em.createNamedQuery("Member.getMemberCount").getSingleResult();
            return count;
        } finally {
            em.close();
        }

    }

}
