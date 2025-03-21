package com.example.week3.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Rob Brzostek, N01556942
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {
    private int id;
    private String name;
    private String category;
    private Double price;
}