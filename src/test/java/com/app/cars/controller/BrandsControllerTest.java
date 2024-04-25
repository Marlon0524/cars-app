package com.app.cars.controller;

import com.app.cars.model.ApiResponse;
import com.app.cars.model.Brands;
import com.app.cars.repository.BrandsRepository;
import com.app.cars.service.BrandsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandsControllerTest {
    @Mock
    private BrandsService brandsService;
    @Mock
    private BrandsRepository brandsRepository;
    @InjectMocks
    private BrandsController brandsController;

    @Test
    void createBrands() {
        BrandsService brandsService = mock(BrandsService.class);
        BrandsController controller = new BrandsController(brandsService);
        Brands mockBrands = new Brands();

        when(brandsService.createBrands(any())).thenReturn(ResponseEntity.ok(new ApiResponse<>(mockBrands, "success", "Marca creada satisfactoriamente")));

        ResponseEntity<ApiResponse<Brands>> responseEntity = controller.createBrands(mockBrands);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("Marca creada satisfactoriamente", responseEntity.getBody().getMessage());
        assertEquals(mockBrands, responseEntity.getBody().getData());

        verify(brandsService, times(1)).createBrands(mockBrands);

    }
}