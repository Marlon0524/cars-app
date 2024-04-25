package com.app.cars.service;

import com.app.cars.model.ApiResponse;
import com.app.cars.model.Brands;
import com.app.cars.model.Cars;
import com.app.cars.repository.BrandsRepository;
import com.app.cars.repository.CarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarsService {
    private final CarsRepository carsRepository;
    private final BrandsRepository brandsRepository;

    public ResponseEntity<ApiResponse<Cars>> createCars(Cars cars) {
        try {

            //obtenr el id de la marca del vehiculo
            Integer brandId = cars.getBrands().getBrand_id();

            //verificar si la marca existe
            Optional<Brands> existingBrandsOpcional = brandsRepository.findById(brandId);
            if (existingBrandsOpcional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(null, "error", "marca no encontrada con el ID " + brandId));
            }
            //obtener la marca existente en la bd
            Brands existingBrands = existingBrandsOpcional.get();

            // asignar la marca existente al carro
            cars.setBrands(existingBrands);

            //guardar el carro en la bd
            Cars newCars = carsRepository.save(cars);

            //construir el objeto apiresponse con el carro creado
            ApiResponse<Cars> response = new ApiResponse<>(newCars, "success", "Auto creado con éxito");

            //devolver el reponseentity con el apiresponse
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "error", "error al crear el carro" + e.getMessage()));
        }
    }

    public ResponseEntity<ApiResponse<Cars>> getCarsById(@PathVariable Integer id) {
        try {
            Optional<Cars> optionalCars = carsRepository.findById(id);
            if (optionalCars.isPresent()) {
                Cars cars = optionalCars.get();
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ApiResponse<>(cars, "success", "Auto encontrado con éxito"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(null, "error", "Auto no encontrado con el Id: " + id));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "error", "Error al obtener el automovil " + e.getMessage()));
        }
    }

    public Page<Cars> getAllCars(Pageable pageable) {
        return carsRepository.findAll(pageable);
    }
   public List<Cars> filterCars(Integer model, Double minPrice, Double maxPrice, Integer mileage) {
        return carsRepository.findCarsByFilters(model, minPrice, maxPrice, mileage);
    }


}
