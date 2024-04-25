package com.app.cars.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import javax.validation.constraints.Size;

@Entity
@Data
public class Brands {
    @Id
    @GeneratedValue
    private Integer brand_id;
    @Size(max = 20, message = "El nombre no puede tener más de 20 caracteres")
    private String name;

    public void setId(int i) {
    }
}
