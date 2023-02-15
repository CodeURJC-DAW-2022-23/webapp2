package com.urjc.asociationplatflorm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.urjc.asociationplatflorm.Model.User;
import com.urjc.asociationplatflorm.Service.UserService;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(Model model, User user){
        if(!userService.existEmail(user.getEmail())){
            userService.save(user);
            return "redirect:/login";
        }else{
            return "login";
        }  
    }
}
