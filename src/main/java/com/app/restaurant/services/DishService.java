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
        System.out.println("############### " + " Fetch all dishes");
        return dishRepository.findAll();
    }

    public Dish getDishById(Long id) {
        System.out.println("############### Searching for dish " + id );
        return dishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dish not found"));
    }

    public Dish createDish(Dish dish) {
        System.out.println("############### " + dish +  " Creating dish");
        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, Dish dish) {
        Dish existingDish = getDishById(id);
        System.out.println("############### " + existingDish +  " to be updated");
        existingDish.setName(dish.getName());
        existingDish.setDescription(dish.getDescription());
        existingDish.setPrice(dish.getPrice());
        existingDish.setImageUrl(dish.getImageUrl());
        System.out.println("############### " + existingDish +  " updated.");
        return dishRepository.save(existingDish);
    }

    public void deleteDish(Long id) {
        System.out.println("############### " + "Deleting dish " +  id);
        dishRepository.deleteById(id);
    }
}
