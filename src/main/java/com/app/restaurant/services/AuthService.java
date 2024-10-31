package com.app.restaurant.services;

import com.app.restaurant.dto.AuthRequest;
import com.app.restaurant.dto.RegisterRequest;
import com.app.restaurant.entities.User;
import com.app.restaurant.exceptions.UnauthorizedException;
import com.app.restaurant.repositories.UserRepository;
import com.app.restaurant.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    // Regex for validating email format
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

    public AuthService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
                       AuthenticationManager authenticationManager,
                       UserDetailsService userDetailsService,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(RegisterRequest registerRequest) {
        // Check if the email is already in use
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException(registerRequest.getEmail() + " Email already in use.");
        }

        // Create a new user
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Encrypt the password
        return userRepository.save(user);
    }

    public String loginUser(AuthRequest authRequest) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("############### " + authentication.getName() +  " generate token to login");

            // Generate JWT token based on authenticated user information
            return jwtTokenProvider.generateToken(authRequest.getEmail()); // or use authentication.getPrincipal()

        } catch (Exception e) {
            throw new UnauthorizedException(e + "Invalid email or password.");
        }
    }
}
