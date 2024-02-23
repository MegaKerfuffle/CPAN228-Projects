package com.example.week7.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.week7.services.DishService;


@Controller
public class AuthController 
    extends RestaurantController
    implements ErrorController {

    protected AuthController(DishService dishService) {
        super(dishService);
    }


    @GetMapping("/error")
    public String getError403(Model model) {
        return getTemplate("auth/error", model);
    }


    @GetMapping("/login")
    public String getLogin(Model model) {
        return getTemplate("auth/login", model);
    }
    


}
