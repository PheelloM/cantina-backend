package com.app.restaurant.services;

import com.app.restaurant.entities.Dish;
import com.app.restaurant.exceptions.ResourceNotFoundException;
import com.app.restaurant.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Dish getDishById(Long id) {
        return dishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dish not found"));
    }

    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, Dish dish) {
        Dish existingDish = getDishById(id);
        existingDish.setName(dish.getName());
        existingDish.setDescription(dish.getDescription());
        existingDish.setPrice(dish.getPrice());
        existingDish.setImageUrl(dish.getImageUrl());
        return dishRepository.save(existingDish);
    }

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}
