package com.example.assignment3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.assignment3.services.BusinessUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final BusinessUserDetailsService detailsService;

    /** Dependency injection of the custom user details service */
    public SecurityConfig(BusinessUserDetailsService detailsService) {
        this.detailsService = detailsService;
    }


    /**
     * Builds a filter chain that defines permissions per-endpoint, and custom
     * pages for login and logout. 
     */
    @Bean
    SecurityFilterChain buildFilterChain(HttpSecurity http) throws Exception {
        // I re-organized this to maximize readability, since I found the way we
        // wrote our filter chain in class to be kinda hard to understand.  
        
        // Set per-request permissions
        http.authorizeHttpRequests(customizer -> {
            // Specify permissions per endpoint
            customizer.requestMatchers("/register/**", "/login/**").permitAll();
            customizer.requestMatchers("/catalog/**").hasAnyRole("GUEST", "EMPLOYEE", "ADMIN");
            customizer.requestMatchers("/admin/**").hasAnyRole("EMPLOYEE", "ADMIN");

            // Require authentication for any endpoints not listed above
            customizer.anyRequest().authenticated();
        });

        // Set custom login page
        http.formLogin(login -> {
            login.loginPage("/login").permitAll();
        });

        // Set custom logout page
        http.logout(logout -> {
            logout.logoutUrl("/logout").logoutSuccessUrl("/catalog/").permitAll();
        });

        return http.build();
    }


    /** Define which resources to ignore, security-wise. */
    @Bean
    WebSecurityCustomizer ignoreResources() {
        return customizer -> {
            customizer.ignoring().requestMatchers("/css/**", "/h2-console/**");
        };
    }


    /**
     * Configure an authentication provider, so Spring Security can know
     * where to get user credentials from. 
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(detailsService);
        provider.setPasswordEncoder(bCryptEncoder());
        return provider;
    }


    /**
     * Creates a new instance of the BCrypt Password Encoder,
     * to keep passwords secure.
     */
    @Bean
    BCryptPasswordEncoder bCryptEncoder() {
        return new BCryptPasswordEncoder();
    }
}
