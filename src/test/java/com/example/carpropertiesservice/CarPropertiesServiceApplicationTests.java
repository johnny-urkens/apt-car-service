package com.example.carpropertiesservice;

import com.example.carpropertiesservice.controller.CarController;
import com.example.carpropertiesservice.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CarPropertiesServiceApplicationTests {


    @Autowired
    private CarController carController;

    @Autowired
    private CarRepository carRepository;

    @Test
    void contextLoads() throws Exception {
        assertThat(carController).isNotNull();
        assertThat(carRepository).isNotNull();
    }

}
