package com.urjc.asociationPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.UserService;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(Model model, User user){
        // if(!userService.existEmail(user.getEmail())){
            
        // }else{
        //     return "login";
        // } 
        
        userService.save(user);
        return "redirect:/login";
    }
}
