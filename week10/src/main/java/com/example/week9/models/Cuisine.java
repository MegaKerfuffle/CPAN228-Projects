package com.example.week9.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Can be embedded in other classes
@Embeddable
public class Cuisine {
    private String cuisineName;
    private String country;
}
