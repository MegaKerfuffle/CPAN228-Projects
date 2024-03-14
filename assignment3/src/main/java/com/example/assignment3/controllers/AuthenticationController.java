package com.example.assignment3.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.assignment3.models.BusinessUser;
import com.example.assignment3.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    private final UserService userService;

    /** Dependency injection of user service */
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    //#region GET Mappings

    /** Retrieves the user login page */
    @GetMapping("/login")
    public String getLogin(Model model, @RequestParam(required = false) String msg) {
        model.addAttribute("msg", msg);
        return "authentication/login";
    }
    
    /** Retrieves the user registration page */
    @GetMapping("/register")
    public String getRegister(Model model, @RequestParam(required = false) String msg) {
        model.addAttribute("user", new BusinessUser());
        model.addAttribute("msg", msg);
        return "authentication/register";
    }

    /** Handles user log-outs */
    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // Trigger a logout
        new SecurityContextLogoutHandler()
            .logout(request, response, authentication);

        // Redirect to login page
        return "redirect:/login?logout";
    }

    //#endregion
    

    //#region POST Mappings

    @PostMapping("/register")
    public String postRegistration(@ModelAttribute BusinessUser user) {
        // Switch to handle potential errors
        switch (userService.saveUser(user)) {
            case ALREADY_REGISTERED:
                return "redirect:/register?msg=Username taken, please try another!";
            case UNKNOWN_ERROR:
                return "redirect:/register?msg=An unknown error has occured, please try again!";
            default: break;
        }
        
        // Redirect to login page
        return "redirect:/login?msg=Successfully registered! Please sign in.";
    }

    //#endregion
}
