package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name = "Car.deleteAllRows", query = "DELETE from Car"),
    @NamedQuery(name = "Car.getAll", query = "SELECT c FROM Car c"),
    @NamedQuery(name = "Car.getByModel", query = "SELECT c FROM Car c WHERE c.model = :model"),
    @NamedQuery(name = "Car.getByYear", query = "SELECT c FROM Car c WHERE c.year = :year"),
    @NamedQuery(name = "Car.getCarCount", query = " SELECT COUNT(c) FROM Car c"),
    @NamedQuery(name = "Car.getByMake", query = " SELECT c FROM Car c WHERE c.make =:make"),
    @NamedQuery(name = "Car.getByPrice", query = "Select c FROM Car c WHERE c.price = :price"),
    @NamedQuery(name = "Car.getByOwner", query = "Select c FROM Car c WHERE c.owner = :owner"),})
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String model;
    private String make;
    private int year;
    private int price;
    private String owner;
    @Temporal(TemporalType.DATE)
    private Date created;
    
    public Car(String model, String make, int year, int price, String owner) {
        this.model = model;
        this.make = make;
        this.year = year;
        this.price = price;
        this.owner = owner;
        this.created = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Car() {

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
