package com.example.week8.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.week8.models.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    // tries to obtain a user by their username
    public Optional<MyUser> findByUsername(String username);
}
