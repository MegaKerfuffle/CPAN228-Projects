package com.example.week3.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.week3.models.Pet;

@Repository
public class PetRepository {
    static List<Pet> pets = new ArrayList<>();

    static {
        pets.add(Pet.builder()
            .id(1)
            .name("Rocky")
            .build());
    }
}
