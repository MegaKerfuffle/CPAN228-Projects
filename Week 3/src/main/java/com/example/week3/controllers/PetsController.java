package com.example.week3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/pets")
public class PetsController {

    @GetMapping("/")
    public String getHome() {
        return "pets";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }
}
