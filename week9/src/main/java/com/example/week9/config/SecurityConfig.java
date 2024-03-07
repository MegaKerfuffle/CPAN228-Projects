package com.example.week7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> 
            {
                // General admission
                requests.requestMatchers("/restaurant/", "/restaurant/home", "/logout/**").permitAll();
                requests.requestMatchers("/restaurant/**").hasAnyRole("USER", "ADMIN");

                // Admin area - limited to users with 'ADMIN' role
                requests.requestMatchers("/admin/**").hasRole("ADMIN");

                // Require authentication
                requests.anyRequest().authenticated();
            });
    
        
        // Show default Spring login form 
        http.formLogin(Customizer.withDefaults());

        // For custom forms - this is a whole hassle, so its commented for now.
        // http.formLogin(form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/restaurant/"));

        return http.build();
    }

    /**
     * Create default users to authenticate
     */
    @Bean
    UserDetailsService defaultUserDetails() {

        // Create a guest user (for customers, I guess?)
        UserDetails defaultUser = User
            .withUsername("guest")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();

        // Create an admin user (for business owner)
        UserDetails adminUser = User
            .withUsername("admin")
            .password(passwordEncoder().encode("password"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(defaultUser, adminUser);
    }

    /**
     * Mark specific resources as ignored, to prevent those from
     * requiring authentication.
     */
    @Bean
    WebSecurityCustomizer ignoreResources() {
        return customizer -> customizer.ignoring().requestMatchers("/css/**", "/h2-console/**");             
    }

    /**
     * Use to encrypt passwords using BCrypt.
     * Plaintext is bad!
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
