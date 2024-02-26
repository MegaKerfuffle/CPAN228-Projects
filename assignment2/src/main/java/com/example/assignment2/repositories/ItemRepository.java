package com.example.assignment2.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assignment2.models.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value = "SELECT * FROM item WHERE brand='?1' AND production_year=?2")
    public List<Item> findByBrandAndYear(String brand, int year);

    // I can't get this to work, which sucks.
    @Query(value = "SELECT * FROM item WHERE brand='?1' AND production_year=?2")
    public Page<Item> findByBrandAndYear(String brand, int year, Pageable pageable);
}
