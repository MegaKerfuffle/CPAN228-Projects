package com.example.week8.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.week8.models.MyUser;
import com.example.week8.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;


    public MyUserDetailsService(UserRepository userRepo) {
        super();
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> userQuery = userRepo.findByUsername(username);
        // Guard clause for ensuring user exists
        if (!userQuery.isPresent()) {
            throw new UsernameNotFoundException("User not found - " + username);
        }

        // Build & return user details for Spring security
        MyUser user = userQuery.get();
        return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRole())
            .build();
    }

}
