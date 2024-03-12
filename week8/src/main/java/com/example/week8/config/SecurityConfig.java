package com.example.week8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
    
    private final UserDetailsService userDetailsService;


    public SecurityConfig(UserDetailsService detailsService) {
        super();
        this.userDetailsService = detailsService;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/restaurant/", "/restaurant/home", "/register/**", "/login/**").permitAll()
                .requestMatchers("/restaurant/menu/").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/restaurant/admin/").hasRole("ADMIN")
                .anyRequest().authenticated())
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer.loginPage("/login").permitAll();
                })
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/restaurant/home/").permitAll());
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

    @Bean
    AuthenticationProvider authProvider() { 
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
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
