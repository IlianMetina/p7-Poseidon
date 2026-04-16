package com.poseidon.controllers;

import com.poseidon.repositories.UserRepository;
import com.poseidon.services.LoginService;
import com.poseidon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        System.out.println("Rentrée controller /login");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("/secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userService.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
