package com.app.restaurant.controllers;

import com.app.restaurant.entities.Dish;
import com.app.restaurant.services.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) {
        return ResponseEntity.ok(dishService.getDishById(id));
    }

    @PostMapping
    public ResponseEntity<Dish> createDish(@RequestBody Dish dish) {
        return ResponseEntity.ok(dishService.createDish(dish));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable Long id, @RequestBody Dish dish) {
        return ResponseEntity.ok(dishService.updateDish(id, dish));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
