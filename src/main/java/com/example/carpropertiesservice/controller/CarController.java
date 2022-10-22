package com.example.carpropertiesservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import java.util.List;
import ch.qos.logback.core.sift.AppenderTracker;
import com.example.carpropertiesservice.model.Car;
import com.example.carpropertiesservice.repository.CarRepository;
import org.hibernate.result.UpdateCountOutput;


@RestController
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostConstruct
    public void fillDB(){
        if(carRepository.count()==0){
            carRepository.save(new Car("Audi A4", 200, 5));
            carRepository.save(new Car("Lamborghini",300,2));
            carRepository.save(new Car("Traktor",30,1));
            carRepository.save(new Car("Tesla",300,4));
            carRepository.save(new Car("Ferarri",300,2));
            carRepository.save(new Car("Volkswagen Golf",180,5));
        }

        System.out.println(carRepository.findCarByCarBrand("Audi A4").getMaxSpeed());
    }

    @GetMapping("/cars/seats/{nrofseats}")
    public List<Car> findBrandByNumberOfSeats(@PathVariable int nrofseats){
        return carRepository.findCarsByNumberOfSeats(nrofseats);
    }

    @GetMapping("/cars/{carBrand}")
    public Car getCarByCarBrand(@PathVariable String carBrand){
        return carRepository.findCarByCarBrand(carBrand);
    }

    @GetMapping("/cars")
    public List<Car> getCarBrands(){
        return carRepository.findAll();
    }

}
