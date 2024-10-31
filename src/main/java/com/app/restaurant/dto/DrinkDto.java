package com.app.restaurant.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DrinkDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
}
