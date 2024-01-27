package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
// Without this, the mappings go to root (/)
@RequestMapping("/pets")
public class HomeMVCController {
    
    @GetMapping("/home")
    public String getHome() {
        return "test";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }
}
