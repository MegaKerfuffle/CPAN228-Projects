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
                // requests.requestMatchers("/restaurant/**").permitAll();
                requests.requestMatchers("/restaurant/", "/restaurant/home", "/logout/**").permitAll();
                requests.requestMatchers("/restaurant/**").hasAnyRole("USER", "ADMIN");

                // Admin area
                requests.requestMatchers("/admin/**").hasRole("ADMIN");

                // Authenticate
                requests.anyRequest().authenticated();
            });
    
        
        http.formLogin(Customizer.withDefaults());
        // http.formLogin(form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/restaurant/"));

        return http.build();
    }

    @Bean
    UserDetailsService defaultUserDetails() {

        UserDetails defaultUser = User
            .withUsername("guest")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();

        UserDetails adminUser = User
            .withUsername("admin")
            .password(passwordEncoder().encode("password"))
            .roles("ADMIN")
            .build();



        return new InMemoryUserDetailsManager(defaultUser, adminUser);
    }


    @Bean
    WebSecurityCustomizer ignoreResources() {
        return customizer -> customizer.ignoring().requestMatchers("/css/**", "/h2-console/**");             
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
