package facades;

import DTO.CarDTO;
import DTO.MemberDTO;
import entities.Car;
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
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CarFacade() {
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

    public CarDTO getByModel() {
        EntityManager em = emf.createEntityManager();
        try {
            Car car = (Car) em.createNamedQuery("Car.getByModel", Car.class).getSingleResult();
            return new CarDTO(car);
        } finally {
            em.close();
        }

    }

    public CarDTO getByOwner() {
        EntityManager em = emf.createEntityManager();
        try {
            Car car = (Car) em.createNamedQuery("Car.getByOwner", Car.class).getSingleResult();
            return new CarDTO(car);
        } finally {
            em.close();
        }

    }

    public CarDTO getByPrice() {
        EntityManager em = emf.createEntityManager();
        try {
            Car car = (Car) em.createNamedQuery("Car.getByPrice", Car.class).getSingleResult();
            return new CarDTO(car);
        } finally {
            em.close();
        }

    }

    public CarDTO getByYear() {
        EntityManager em = emf.createEntityManager();
        try {
            Car car = (Car) em.createNamedQuery("Car.getByYear", Car.class).getSingleResult();
            return new CarDTO(car);
        } finally {
            em.close();
        }

    }

}
