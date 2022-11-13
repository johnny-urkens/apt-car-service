package com.example.carpropertiesservice;

import com.example.carpropertiesservice.model.Car;
import com.example.carpropertiesservice.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    private Car car1 = new Car("Audi",200,5);
    private Car car2 = new Car("Tesla", 150,4);

    @BeforeEach
    public void beforeAllTests() {
        carRepository.deleteAll();
        carRepository.save(car1);
        carRepository.save(car2);
    }

    @AfterEach
    public void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        carRepository.deleteAll();
    }

    @Test
    void givenCar_whenFindCarByNrOfSeats_thenReturnJsonListCar() throws Exception {

        mockMvc.perform(get("/cars/seats/{nrofseats}",5))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].carBrand",is("audi")))
            .andExpect(jsonPath("$[0].maxSpeed",is(200)))
            .andExpect(jsonPath("$[0].numberOfSeats",is(5)));
    }

    @Test
    void givenCar_whenGetCarByCarBrand_thenReturnJsonCar() throws Exception {

        mockMvc.perform(get("/cars/{carBrand}","audi"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.carBrand",is("audi")))
            .andExpect(jsonPath("$.numberOfSeats",is(5)))
            .andExpect(jsonPath("$.maxSpeed",is(200)));
    }

    @Test
    void givenCars_whenGetAllCars_thenReturnJsonCars() throws Exception {

        mockMvc.perform(get("/cars"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].carBrand",is("audi")))
            .andExpect(jsonPath("$[0].maxSpeed",is(200)))
            .andExpect(jsonPath("$[0].numberOfSeats",is(5)))
            .andExpect(jsonPath("$[1].carBrand",is("tesla")))
            .andExpect(jsonPath("$[1].maxSpeed",is(150)))
            .andExpect(jsonPath("$[1].numberOfSeats",is(4)));
    }


    @Test
    void testSetCarBrand() {
        String carBrand = "seat";
        Car car = new Car();
        car.setCarBrand(carBrand);
        assertEquals(car.getCarBrand(), carBrand);
    }

    @Test
    void testSetMaxSpeed() {
        Integer maxSpeed = 100;
        Car car = new Car();
        car.setMaxSpeed(maxSpeed);
        assertEquals(car.getMaxSpeed(), maxSpeed);
    }


    @Test
    void testSetNrOfSeats() {
        Integer nrOfSeats = 3;
        Car car = new Car();
        car.setNumberOfSeats(nrOfSeats);
        assertEquals(car.getNumberOfSeats(), nrOfSeats);
    }

}
