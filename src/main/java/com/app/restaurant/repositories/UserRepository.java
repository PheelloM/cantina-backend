package com.app.restaurant.repositories;

import com.app.restaurant.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Method to find a User by email
    Optional<User> findByEmail(String email);
}
