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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    private Car car1 = new Car("Mercedes",220,4);
    private Car car2 = new Car("Renault",185,5);
    private Car car3 = new Car("Volvo",215,4);

    @BeforeEach
    public void beforeAllTests() {
        carRepository.deleteAll();
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
    }
    @AfterEach
    public void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        carRepository.deleteAll();
    }

    @Test
    public void givenCars_whenFindBrandByNumberOfSeats_thenReturnJsonCars() throws Exception{


        mockMvc.perform(get("/cars/seats/{nrofseats}",4))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].carBrand", is("Mercedes")))
            .andExpect(jsonPath("$[0].maxSpeed", is(220)))
            .andExpect(jsonPath("$[0].numberOfSeats",is(4)))
            .andExpect(jsonPath("$[1].carBrand", is("Volvo")))
            .andExpect(jsonPath("$[1].maxSpeed", is(215)))
            .andExpect(jsonPath("$[1].numberOfSeats",is(4)));

    }
    @Test
    public void givenCar_whenFindCarByCarBrand_thenReturnJsonCar() throws Exception{


        mockMvc.perform(get("/cars/{carBrand}","Volvo"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.carBrand",is("Volvo")))
            .andExpect(jsonPath("$.maxSpeed",is(215)))
            .andExpect(jsonPath("$.numberOfSeats",is(4)));

    }
    @Test
    public void givenCars_whenFindAll_thenReturnJsonCars() throws Exception{


        mockMvc.perform(get("/cars",4))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].carBrand", is("Mercedes")))
            .andExpect(jsonPath("$[0].maxSpeed", is(220)))
            .andExpect(jsonPath("$[0].numberOfSeats",is(4)))
            .andExpect(jsonPath("$[1].carBrand", is("Renault")))
            .andExpect(jsonPath("$[1].maxSpeed", is(185)))
            .andExpect(jsonPath("$[1].numberOfSeats",is(5)))
            .andExpect(jsonPath("$[2].carBrand", is("Volvo")))
            .andExpect(jsonPath("$[2].maxSpeed", is(215)))
            .andExpect(jsonPath("$[2].numberOfSeats",is(4)));

    }
}
