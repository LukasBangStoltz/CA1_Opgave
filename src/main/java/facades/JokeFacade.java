package facades;

import DTO.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<JokeDTO> getAllJokes() {
        EntityManager em = emf.createEntityManager();

        List<Joke> allJokes = null;
        List<JokeDTO> allJokesDTO = new ArrayList();

        try {
            allJokes = em.createQuery("SELECT j FROM Joke j", Joke.class).getResultList();

            allJokes.forEach((Joke joke) -> {

                allJokesDTO.add(new JokeDTO(joke));

            });

            return allJokesDTO;
        } finally {
            em.close();
        }

    }

}


