package com.example.demo.models;

import lombok.Data;

@Data
public class Fighter {
    private final String name;
    private final int damagePerHit;
    private final int health;
    private final double resistance;
    private final Anime animeFrom;

    public enum Anime {
        NARUTO, BLEACH, TEKKEN, ONE_PIECE, FULL_METAL_ALCHEMIST
    }
}
