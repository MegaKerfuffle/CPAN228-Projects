package com.example.week6.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.week6.models.Dish;
import java.util.List;



@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    // public List<Dish> findByCategoryAndPrice(String category, Double price);

    @Query(value = "SELECT * FROM dish WHERE category=lower(?1) AND price=?2")
    public List<Dish> findByCategoryAndPrice(String category, Double price);

}
