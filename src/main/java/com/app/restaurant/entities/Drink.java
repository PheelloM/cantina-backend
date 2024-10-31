package com.app.restaurant.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NonNull;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
@Data
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    public Drink() {}
}
