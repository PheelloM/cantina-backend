package com.app.restaurant.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.app.restaurant.dto.AuthRequest;
import com.app.restaurant.dto.RegisterRequest;
import com.app.restaurant.entities.User;

import com.app.restaurant.exceptions.UnauthorizedException;
import com.app.restaurant.security.JwtTokenProvider;
import com.app.restaurant.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


import java.util.Optional;

public class AuthServiceTest {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AuthService authService; // Your service class that contains registerUser method

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //login tests

    @Test
    void loginUser_ShouldReturnToken_WhenAuthenticationIsSuccessful() {
        // Arrange
        String email = "user@example.com";
        String password = "password";
        String expectedToken = "jwtToken";

        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(email);
        authRequest.setPassword(password);

        when(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)))
                .thenReturn(authentication);

        when(jwtTokenProvider.generateToken(email)).thenReturn(expectedToken);

        // Act
        String token = authService.loginUser(authRequest);

        // Assert
        assertEquals(expectedToken, token, "The returned token should match the expected token");
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenProvider, times(1)).generateToken(email);
    }

    @Test
    void loginUser_ShouldThrowUnauthorizedException_WhenAuthenticationFails() {
        // Arrange
        String email = "user@example.com";
        String password = "wrongPassword";

        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(email);
        authRequest.setPassword(password);

        when(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)))
                .thenThrow(new RuntimeException("Authentication failed"));

        // Act & Assert
        UnauthorizedException exception = assertThrows(UnauthorizedException.class,
                () -> authService.loginUser(authRequest),
                "Expected loginUser to throw UnauthorizedException when authentication fails");

        assertTrue(exception.getMessage().contains("Invalid email or password"));
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenProvider, never()).generateToken(email);
    }

}
