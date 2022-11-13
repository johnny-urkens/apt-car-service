package com.example.carpropertiesservice.model;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String carBrand;

    private Integer maxSpeed;

    private Integer numberOfSeats;

    public Car() {
    }

    public Car(String carBrand, Integer maxSpeed, Integer numberOfSeats) {
        this.carBrand = carBrand.toLowerCase();
        this.maxSpeed = maxSpeed;
        this.numberOfSeats = numberOfSeats;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand.toLowerCase();
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

}
