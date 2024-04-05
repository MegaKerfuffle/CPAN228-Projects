package com.example.week11.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class DefaultConfig implements DatabaseConfig {

    @Override
    public String setupDbConnection() {
        return "Setting up connection for Default DB";
    }

}
