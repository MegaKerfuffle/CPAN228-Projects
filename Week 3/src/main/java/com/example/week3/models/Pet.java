package com.example.week3.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pet {
    int id;
    String name;
}
