package com.app.restaurant.services;

import com.app.restaurant.entities.Drink;
import com.app.restaurant.exceptions.ResourceNotFoundException;
import com.app.restaurant.repositories.DrinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkService {

    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public Drink getDrinkById(Long id) {
        return drinkRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Drink not found"));
    }

    public Drink createDrink(Drink drink) {
        return drinkRepository.save(drink);
    }

    public Drink updateDrink(Long id, Drink drink) {
        Drink existingDrink = getDrinkById(id);
        existingDrink.setName(drink.getName());
        existingDrink.setDescription(drink.getDescription());
        existingDrink.setPrice(drink.getPrice());
        existingDrink.setImageUrl(drink.getImageUrl());
        return drinkRepository.save(existingDrink);
    }

    public void deleteDrink(Long id) {
        drinkRepository.deleteById(id);
    }
}
