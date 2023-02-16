package com.urjc.asociationPlatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String goHome(Model model){
        return "home";
    }

    @GetMapping("/login")
    public String goLogin(Model model){
        return "login";
    }
}
