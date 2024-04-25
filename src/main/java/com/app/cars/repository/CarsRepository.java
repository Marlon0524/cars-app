package com.app.cars.repository;

import com.app.cars.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Integer> {
    @Query(value = "SELECT * FROM carsdb.cars c " +
            "WHERE" +
            "(c.model = ?1 OR ?1 IS NULL) AND" +
            "(c.price >= ?2 OR ?2 IS NULL) AND" +
            "(c.price <= ?3 OR ?3 IS NULL) AND" +
            "(c.mileage = ?4 OR ?4 IS NULL)\n" +
            "LIMIT 0, 1000;\n", nativeQuery = true)
    List<Cars> findCarsByFilters(
            Integer model, Double minPrice, Double maxPrice, Integer mileage);
}

