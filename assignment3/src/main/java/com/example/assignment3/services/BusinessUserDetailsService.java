package com.example.assignment3.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.assignment3.models.BusinessUser;
import com.example.assignment3.repositories.UserRepository;

@Service
public class BusinessUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;


    /** Dependency injection of user repository */
    public BusinessUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    /**
     * Turns our user instances into `UserDetails` instances that Spring Security 
     * uses for authentication.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<BusinessUser> userQuery = userRepo.findByUsername(username);
        // Ensure user exists; throw exception if not.
        if (!userQuery.isPresent()) {
            throw new UsernameNotFoundException("User couldn't be found by given username: " + username);
        }

        // User definitely exists at this point, so extract it to
        // build and return a `UserDetails` instance.
        BusinessUser user = userQuery.get();
        return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRole())
            .build();
    }
}