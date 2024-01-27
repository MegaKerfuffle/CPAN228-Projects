package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// RestController always returns JSON data - not HTML files
@RestController
public class AnimeController {
    @GetMapping("/api/anime")    
    public String[] getAnimes() {
        return new String[] { "One Piece", "Titanic" };
    }

}
