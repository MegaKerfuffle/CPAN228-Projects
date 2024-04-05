package com.example.week11.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.week11.services.DatabaseConfig;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class Controller {

    private final DatabaseConfig dbConfig;

    public Controller(DatabaseConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    @GetMapping("/")
    public String getDbConfig() {
        return dbConfig.setupDbConnection();
    }
    
}
