package com.urjc.asociationplatflorm.Controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/redirectLogin")

    public String redirectLogin(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();

        if(principal!=null){
            if(request.isUserInRole("admin")){
                return "redirect:/users";
            }else if(request.isUserInRole("aso")){
                return "redirect:/myAso";
            }else{
                return "redirect:/home";
            }
        }
        else{
            
            return null;
        }
    }
    
}
