package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Pet;

public class PetRepository {
    private static List<Pet> pets = new ArrayList<Pet>();

    // you can do this in java??? huh??
    // anon static method?
    static {

        // TODO: Implement builder for `Pet`
        /*
        pets.add(Pet.builder()
            .setId(1)
            .setName("Rocky"));
         */
    }
}
