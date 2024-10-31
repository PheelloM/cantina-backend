package com.app.restaurant.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class AuthRequest {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    // Default constructor
    public AuthRequest() {}

    // Parameterized constructor
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
