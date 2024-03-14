package com.example.assignment3.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.assignment3.models.BusinessUser;
import com.example.assignment3.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder bCryptEncoder;


    /** Dependency injection of user repository and BCrypt password encoder */
    public UserService(UserRepository userRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.bCryptEncoder = encoder;
    }

    /** Save a newly registered user to the database. */
    public RegistrationStatus saveUser(BusinessUser user) {
        // Guard clauses to prevent registration in certain circumstances.
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            return RegistrationStatus.ALREADY_REGISTERED;
        }
        // NOTE: More validation could go here if required (min PW length, usernames, etc.)

        // Encode the user's password before sending to repo
        String encodedPassword = bCryptEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Save the user to DB, return success status
        userRepo.save(user);
        return RegistrationStatus.SUCCESS;
    }
    
    /** Provides more details about a user's registration status */
    public enum RegistrationStatus {
        ALREADY_REGISTERED,
        SUCCESS,
        UNKNOWN_ERROR
    }
}
