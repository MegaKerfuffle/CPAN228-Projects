package com.example.week7.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.week7.services.DishService;

@Controller
public class AuthController 
    extends ViewControllerBase
    implements ErrorController {

    protected AuthController(DishService dishService) {
        super(dishService);
    }


    // This isn't just for 403's - meaning, it could block
    // the wall of text errors that Spring spits out, which
    // are helpful sometimes while working on the project
    @GetMapping("/error")
    public String getError403(Model model) {
        return getTemplate("auth/error", model);
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return getTemplate("auth/login", model);
    }
}
