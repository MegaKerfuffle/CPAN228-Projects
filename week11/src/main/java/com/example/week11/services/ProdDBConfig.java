package com.example.week11.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdDBConfig implements DatabaseConfig {

    @Override
    public String setupDbConnection() {
        return "Setting up connection for Prod DB";
    }
}
