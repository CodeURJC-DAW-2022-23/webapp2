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

    @GetMapping("/logerror")
    public String gologerror(Model model){
        return "404";
    }

    @GetMapping("/private")
    public String goprivate(Model model){
        return "myAso";
    }
}
