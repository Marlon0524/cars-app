package com.app.cars.service;

import com.app.cars.model.ApiResponse;
import com.app.cars.model.Brands;
import com.app.cars.repository.BrandsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandsService {
    private final BrandsRepository brandsRepository;

    public ResponseEntity<ApiResponse<Brands>> createBrands(Brands brands){
        try{

            Brands newBrands = brandsRepository.save(brands);
            ApiResponse<Brands> response = new ApiResponse<>(newBrands,"success","Marca creada correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>(null, "error","Error al crear la marca" + e.getMessage()));
        }
    }
}
