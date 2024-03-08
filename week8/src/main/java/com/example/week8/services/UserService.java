package com.example.week8.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.week8.models.MyUser;
import com.example.week8.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepo, BCryptPasswordEncoder encoder) {
        super();
        this.userRepo = userRepo;
        this.encoder = encoder;
    }


    public RegistrationState saveUser(MyUser user) {
        // check if exists
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            // nuh uh
            return RegistrationState.ALREADY_EXISTS;
        }

        // Encrypt password and save user to DB
        String encrypted = encoder.encode(user.getPassword()); 
        user.setPassword(encrypted);

        userRepo.save(user);
        
        return RegistrationState.REGISTERED;
    }


    public enum RegistrationState {
        ALREADY_EXISTS,
        REGISTERED,
        UNKNOWN_ERROR
    }
}
