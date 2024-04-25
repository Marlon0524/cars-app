package com.app.cars.service;

import com.app.cars.model.ApiResponse;
import com.app.cars.model.Brands;
import com.app.cars.repository.BrandsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandsServiceTest {
    @Mock
    private BrandsRepository brandsRepository;
    @InjectMocks
    private BrandsService brandsService;

    @Test
    void createBrands() {
        BrandsRepository brandsRepository = mock(BrandsRepository.class);
        BrandsService brandsService = new BrandsService(brandsRepository);
        Brands mockBrands = new Brands();
        Brands saveBrand = new Brands();

        when(brandsRepository.save(any())).thenReturn(saveBrand);

        ResponseEntity<ApiResponse<Brands>> responseEntity = brandsService.createBrands(mockBrands);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("Marca creada correctamente", responseEntity.getBody().getMessage());
        assertEquals(saveBrand, responseEntity.getBody().getData());

    }
}