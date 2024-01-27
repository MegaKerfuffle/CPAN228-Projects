package com.example.demo.repositories;

import java.util.List;

import com.example.demo.models.Fighter;

import lombok.Data;

@Data
public class CharacterPool {
    private List<Fighter> fighters;
    
    public void addHero(Fighter fighter) {
        fighters.add(fighter);
    }
}
