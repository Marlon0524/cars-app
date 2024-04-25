package com.app.cars.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@Entity
@Data
public class Cars {
    @Id
    @GeneratedValue
    private Integer car_id;
    @Size(max = 30, message = "El modelo no puede tener más de 30 caracteres")
    private String model;
    @Size(max = 100, message = "La descripción no puede tener más de 100 caracteres")
    private String description;
    private Double price;
    private Integer mileage;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brands brands;

    public void setId(int i) {
    }
}
