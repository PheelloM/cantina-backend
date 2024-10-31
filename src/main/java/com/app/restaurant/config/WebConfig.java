package com.app.restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow CORS for all paths
                        .allowedOrigins("http://localhost:4200",  "http://localhost:8080") // Specify the allowed origins (e.g., frontend app)
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify allowed methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Allow cookies or authorization headers
            }
        };
    }
}
