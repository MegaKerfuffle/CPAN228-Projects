package com.example.week4.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.week4.models.Dish;

@Repository
public class DishRepository {
    
    @Autowired
    JdbcTemplate template;


    public int save(Dish dish) {
        String sql = "INSERT INTO dish(name, category, price) VALUES(?, ?, ?)";
        return template.update(sql, dish.getName(), dish.getCategory(), dish.getPrice());
    }


    public List<Dish> findAll() {
        String sql = "SELECT * FROM dish";


        RowMapper<Dish> mapper = new RowMapper<Dish>() {
            @Override
            public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
                Dish myDish = new Dish();
                
                myDish.setId(rs.getInt(1));                
                myDish.setName(rs.getString(2));
                myDish.setCategory(rs.getString(3));
                myDish.setPrice(rs.getDouble(4));
             
                return myDish;
            }
        };

        return template.query(sql, mapper);
    }
}
