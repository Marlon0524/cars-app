package com.app.cars.controller;

import com.app.cars.model.ApiResponse;
import com.app.cars.model.Cars;
import com.app.cars.repository.CarsRepository;
import com.app.cars.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CarsController {
    private final CarsService carsService;
    private final CarsRepository carsRepository;
    private static final Logger logger =Logger.getLogger(CarsController.class.getName());
    @PostMapping
    public ResponseEntity<ApiResponse<Cars>> createCars(@RequestBody Cars cars) {
        return carsService.createCars(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Cars>> getCarsById(@PathVariable Integer id) {
        return carsService.getCarsById(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Cars>>> getAllCars(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        logger.info("Llamada al endpoint GET /api recibida.");
        Page<Cars> carsPage = carsService.getAllCars(PageRequest.of(page, size));
        if (carsPage != null && carsPage.hasContent()) {
            ApiResponse<Page<Cars>> response = new ApiResponse<>(carsPage, "success", "Autos obtenidos satisfactoriamente.");
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Page<Cars>> response = new ApiResponse<>(null, "error", "No se encontraron autos.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/filters")
    public ResponseEntity<ApiResponse<List<Cars>>> getCarsByFilters(
            @RequestParam(required = false) Integer model,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer mileage) {
        List<Cars> cars = carsRepository.findCarsByFilters(model, minPrice, maxPrice, mileage);
        if (cars.isEmpty()) {
            ApiResponse<List<Cars>> response = new ApiResponse<>(null, "error", "No se encontraron autos con los filtros proporcionados");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            ApiResponse<List<Cars>> response = new ApiResponse<>(cars, "Success", "Autos obtenidos satisfactoriamente");
            return ResponseEntity.ok().body(response);
        }


}
}
