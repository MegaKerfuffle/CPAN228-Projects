package com.example.restclient.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Not using JPA, so no need for @Entity or @Id.

/**
 * Represents a single album given by the API we're accessing.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    private int id;
    private int userId;
    private String title;
}
