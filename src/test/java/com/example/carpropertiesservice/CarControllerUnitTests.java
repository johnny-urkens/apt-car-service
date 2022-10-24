package com.example.carpropertiesservice;


import com.example.carpropertiesservice.model.Car;
import com.example.carpropertiesservice.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean

    private CarRepository carRepository;

    private Car car1 = new Car("Mercedes",220,4);
    private Car car2 = new Car("Renault",185,5);
    private Car car3 = new Car("Volvo",215,4);

    private List<Car> cars4Seats = Arrays.asList(car1,car3);


    @Test
    public void givenCars_whenFindBrandByNumberOfSeats_thenReturnJsonCars() throws Exception{
        given(carRepository.findCarsByNumberOfSeats(4)).willReturn(cars4Seats);

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


}
