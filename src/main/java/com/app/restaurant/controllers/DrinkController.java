package com.app.restaurant.controllers;

import com.app.restaurant.entities.Drink;
import com.app.restaurant.services.DrinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {
    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public List<Drink> getAllDrinks() {
        return drinkService.getAllDrinks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drink> getDrinkById(@PathVariable Long id) {
        return ResponseEntity.ok(drinkService.getDrinkById(id));
    }

    @PostMapping
    public ResponseEntity<Drink> createDrink(@RequestBody Drink drink) {
        return ResponseEntity.ok(drinkService.createDrink(drink));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Drink> updateDrink(@PathVariable Long id, @RequestBody Drink drink) {
        return ResponseEntity.ok(drinkService.updateDrink(id, drink));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable Long id) {
        drinkService.deleteDrink(id);
        return ResponseEntity.noContent().build();
    }

}
