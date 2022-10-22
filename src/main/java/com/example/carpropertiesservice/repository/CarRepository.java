package com.example.carpropertiesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.carpropertiesservice.model.Car;


@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findCarByCarBrand(String carBrand);
    List<Car> findCarsByNumberOfSeats(int nrofseats);
    List<Car> findAll();
}
