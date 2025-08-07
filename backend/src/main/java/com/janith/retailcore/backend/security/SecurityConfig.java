package com.janith.retailcore.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // Tells Spring this is a configuration class
@EnableWebSecurity // Enables Spring Security's web security support
public class SecurityConfig {

    @Bean // A @Bean is a method that produces an object to be managed by the Spring container
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // This is where we define our custom security rules.
        http
                // 1. Authorize all HTTP requests.
                .authorizeHttpRequests(auth -> auth
                        // Any request that comes in needs to be authenticated.
                        .anyRequest().authenticated()
                )
                // 2. Use HTTP Basic authentication (the username/password popup).
                .httpBasic(withDefaults())
                // 3. THIS IS THE CRITICAL FIX: Disable CSRF protection.
                // We use a lambda expression here for cleaner configuration.
                .csrf(AbstractHttpConfigurer::disable);

        // Build and return the security filter chain.
        return http.build();
    }
}
