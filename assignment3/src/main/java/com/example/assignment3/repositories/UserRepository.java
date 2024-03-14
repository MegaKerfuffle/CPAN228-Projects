package com.example.assignment3.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.assignment3.models.BusinessUser;


public interface UserRepository extends JpaRepository<BusinessUser, Long> {

    /** Query the database for a user, by their username. */
    public Optional<BusinessUser> findByUsername(String username);
}
