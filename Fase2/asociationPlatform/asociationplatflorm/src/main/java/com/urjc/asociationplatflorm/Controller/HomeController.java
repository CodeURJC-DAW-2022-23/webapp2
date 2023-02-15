package com.urjc.asociationplatflorm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String gohome(Model model){

        return "home";
    }

    @GetMapping("/login")
    public String gologin(Model model){
        
        return "login";
    }

}
