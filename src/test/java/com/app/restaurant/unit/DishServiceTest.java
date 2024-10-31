package com.app.restaurant.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.app.restaurant.exceptions.ResourceNotFoundException;
import com.app.restaurant.entities.Dish;
import com.app.restaurant.repositories.DishRepository;
import com.app.restaurant.services.DishService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class DishServiceTest {
    @Mock
    private DishRepository dishRepository;

    @InjectMocks
    private DishService dishService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDishById_ShouldReturnDish_WhenDishExists() {
        // Arrange
        Long dishId = 1L;
        Dish expectedDish = new Dish();
        expectedDish.setId(dishId);
        when(dishRepository.findById(dishId)).thenReturn(Optional.of(expectedDish));

        // Act
        Dish actualDish = dishService.getDishById(dishId);

        // Assert
        assertEquals(expectedDish, actualDish, "The dish returned should match the expected dish");
        verify(dishRepository, times(1)).findById(dishId);
    }

    @Test
    void getDishById_ShouldThrowResourceNotFoundException_WhenDishDoesNotExist() {
        // Arrange
        Long dishId = 1L;
        when(dishRepository.findById(dishId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> dishService.getDishById(dishId),
                "Expected getDishById to throw ResourceNotFoundException when dish is not found");

        assertEquals("Dish not found", exception.getMessage());
        verify(dishRepository, times(1)).findById(dishId);
    }
}
