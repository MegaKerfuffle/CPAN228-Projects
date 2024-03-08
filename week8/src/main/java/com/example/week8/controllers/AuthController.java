package com.example.week8.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.week8.models.MyUser;
import com.example.week8.services.DishService;
import com.example.week8.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController 
    extends ViewControllerBase
    implements ErrorController {

    private final UserService userService;

    protected AuthController(DishService dishService, UserService userService) {
        super(dishService);
        this.userService = userService;
    }


    // This isn't just for 403's - meaning, it could block
    // the wall of text errors that Spring spits out, which
    // are helpful sometimes while working on the project
    @GetMapping("/error")
    public String getError403(Model model) {
        return getTemplate("auth/error", model);
    }

    @GetMapping("/login")
    public String getLogin(Model model, @RequestParam(required = false) String msg) {
        model.addAttribute("msg", msg);
        return getTemplate("auth/login", model);
    }

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        new SecurityContextLogoutHandler()
            .logout(request, response, authentication);
        return "redirect:/login?msg=You have been logged out!";
    }
    

    @GetMapping("/register")
    public String getRegistration(Model model, @RequestParam(required = false) String msg) {
        model.addAttribute("user", new MyUser());
        model.addAttribute("msg", msg);
        return getTemplate("auth/register", model);
    }

    @PostMapping("/register")
    public String postRegistration(@ModelAttribute MyUser user) {
        // add to db
        // note: could expand to a switch if there's more errors in the future
        if (userService.saveUser(user) == UserService.RegistrationState.ALREADY_EXISTS) {
            String error = "User already exists!";
            return "redirect:/register?msg=" + error;
        }
    
        // redir to login
        return "redirect:/login?msg=Registered successfully!";
    }   
}