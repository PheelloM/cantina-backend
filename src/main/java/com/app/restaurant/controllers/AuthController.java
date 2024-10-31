package com.app.restaurant.controllers;

import com.app.restaurant.dto.AuthRequest;
import com.app.restaurant.dto.RegisterRequest;
import com.app.restaurant.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody AuthRequest authRequest) {
        String token = authService.loginUser(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.registerUser(registerRequest);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}
