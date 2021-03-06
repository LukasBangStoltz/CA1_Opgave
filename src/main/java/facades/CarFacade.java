package facades;

import DTO.CarDTO;
import entities.Car;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CarFacade() {
    }

    public static CarFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public long countAllCars() {
        EntityManager em = emf.createEntityManager();
        try {
            long count = (long) em.createNamedQuery("Car.getCarCount").getSingleResult();
            return count;
        } finally {
            em.close();
        }

    }

    public List<CarDTO> getAllCars() {
        EntityManager em = emf.createEntityManager();

        List<Car> allCars = null;
        List<CarDTO> allCarsDTO = new ArrayList();

        try {
            allCars = em.createNamedQuery("Car.getAll", Car.class).getResultList();

            allCars.forEach((Car car) -> {

                allCarsDTO.add(new CarDTO(car));

            });

            return allCarsDTO;
        } finally {
            em.close();
        }

    }

    public List<CarDTO> getByModel(String model) {
        EntityManager em = emf.createEntityManager();

        List<Car> allCars = null;
        List<CarDTO> allCarsDTO = new ArrayList();

        try {
            Query query = em.createNamedQuery("Car.getByModel", Car.class);
            query.setParameter("model", model);
            allCars = query.getResultList();

            allCars.forEach((Car car) -> {

                allCarsDTO.add(new CarDTO(car));

            });

            return allCarsDTO;
        } finally {
            em.close();
        }

    }

    public List<CarDTO> getByPrice(int price) {
        EntityManager em = emf.createEntityManager();

        List<Car> allCars = null;
        List<CarDTO> allCarsDTO = new ArrayList();

        try {
            Query query = em.createNamedQuery("Car.getByPrice", Car.class);
            query.setParameter("price", price);
            allCars = query.getResultList();
            allCars.forEach((Car car) -> {

                allCarsDTO.add(new CarDTO(car));

            });

            return allCarsDTO;
        } finally {
            em.close();
        }

    }

    public List<CarDTO> getByYear(int year) {
        EntityManager em = emf.createEntityManager();

        List<Car> allCars = null;
        List<CarDTO> allCarsDTO = new ArrayList();

        try {
            Query query = em.createNamedQuery("Car.getByYear", Car.class);
            query.setParameter("year", year);
            allCars = query.getResultList();

            allCars.forEach((Car car) -> {

                allCarsDTO.add(new CarDTO(car));

            });

            return allCarsDTO;
        } finally {
            em.close();
        }

    }
    
      public List<CarDTO> getByMake(String make) {
        EntityManager em = emf.createEntityManager();

        List<Car> allCars = null;
        List<CarDTO> allCarsDTO = new ArrayList();

        try {
            Query query = em.createNamedQuery("Car.getByMake", Car.class);
            query.setParameter("make", make);
            allCars = query.getResultList();

            allCars.forEach((Car car) -> {

                allCarsDTO.add(new CarDTO(car));

            });

            return allCarsDTO;
        } finally {
            em.close();
        }

    }

}
