package facades;

import DTO.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
            allJokes = em.createNamedQuery("Joke.getAll").getResultList();

            allJokes.forEach((Joke joke) -> {

                allJokesDTO.add(new JokeDTO(joke));

            });

            return allJokesDTO;
        } finally {
            em.close();
        }

    }

    public JokeDTO getJokeById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Joke joke = em.find(Joke.class, id);
            return new JokeDTO(joke);
        } finally {
            em.close();
        }
    }

    public List<JokeDTO> getJokeByType(String jokeType) {
        EntityManager em = emf.createEntityManager();
        List<Joke> jokeList = null;
        List<JokeDTO> jokeDTOlist = new ArrayList();

        try {
            Query query = em.createNamedQuery("Joke.getByType");
            query.setParameter("jokeType", jokeType);
            jokeList = query.getResultList();

            jokeList.forEach((Joke joke) -> {

                jokeDTOlist.add(new JokeDTO(joke));

            });
            return jokeDTOlist;
        } finally {
            em.close();
        }

    }

    public JokeDTO getRandomJoke() {

        EntityManager em = emf.createEntityManager();
        try {
            Random random = new Random();
            long id = random.nextInt(10 - 1 + 1) + 1;
            Joke joke = em.find(Joke.class, id);
            return new JokeDTO(joke);
        } finally {
            em.close();
        }

    }

}
