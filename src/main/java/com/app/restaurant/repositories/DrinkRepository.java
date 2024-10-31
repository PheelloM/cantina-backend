package com.app.restaurant.repositories;

import com.app.restaurant.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
}
