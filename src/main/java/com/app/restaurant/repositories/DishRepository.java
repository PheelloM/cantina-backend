package com.app.restaurant.repositories;

import com.app.restaurant.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
