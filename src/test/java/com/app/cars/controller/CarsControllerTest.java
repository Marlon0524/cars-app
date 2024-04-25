package com.app.cars.controller;

import com.app.cars.model.ApiResponse;
import com.app.cars.model.Brands;
import com.app.cars.model.Cars;
import com.app.cars.repository.CarsRepository;
import com.app.cars.service.CarsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class CarsControllerTest {
    private MockMvc mockMvc;
    @Mock
    private CarsService carsService;
    @Mock
    private CarsRepository carsRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private CarsController carsController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void createCars() {
        // Mock del objeto Cars que se espera recibir en el controlador
        Brands brand = new Brands();
        brand.setId(1); // Supongamos que el ID de la marca es 1
        brand.setName("Toyota");
        Cars mockCars = new Cars();
        mockCars.setModel("Toyota corolla");
        mockCars.setDescription("Este es un Toyota corolla.");
        mockCars.setPrice(25000.0);
        mockCars.setMileage(50000);
        mockCars.setBrands(brand); // Asignar el objeto Brands al objeto Cars

        // Mock del controlador
        CarsController carsController = mock(CarsController.class);

        //declaramos apiResponse
        ApiResponse<Cars> apiResponse = new ApiResponse<>();
        apiResponse.setData(mockCars);
        // Mock de la respuesta esperada del controlador
        ResponseEntity<ApiResponse<Cars>> expectedResponse = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        when(carsController.createCars(mockCars)).thenReturn(expectedResponse);

        // Llama al método del controlador que estás probando
        ResponseEntity<ApiResponse<Cars>> actualResponse = carsController.createCars(mockCars);

        // Verifica que la respuesta recibida sea la esperada
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getCarsById() throws Exception {
    }

    @Test
    void getAllCars() throws Exception{

    }

    @Test
    void getCarsByFilters() {
    }
}