package DTO;

import entities.Car;

public class CarDTO {

    private long id;
    private String model;
    private String make;
    private int year;
    private int price;
    private String owner;

    public CarDTO(Car car) {
        this.id = car.getId();
        this.model = car.getModel();
        this.make = car.getMake();
        this.year = car.getYear();
        this.price = car.getPrice();
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }
    

    public String getOwner() {
        return owner;
    }

 
    

}
