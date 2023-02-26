package com.urjc.asociationPlatform.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    User currentUser;

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

	    Principal principal = request.getUserPrincipal();

	 	if(principal != null) {
	 		model.addAttribute("logged", true);

            if(request.isUserInRole("ASO")){
                model.addAttribute("aso",true);
            }else if(request.isUserInRole("BASE")){
                model.addAttribute("base",true);
            }else if(request.isUserInRole("ADMIN")){
                model.addAttribute("admin", true);
            }
			
	 	} else {
	 		model.addAttribute("logged", false);
		}
	}
    
    @GetMapping("/")
    public String goHome(Model model){
        return "home";
    }

    @GetMapping("/login")
    public String goLogin(Model model){
        return "login";
    }

    @RequestMapping("/loginerror")
	public String loginerror() {
		return "loginerror";
	}

    @RequestMapping("/exito")
	public String exito() {
		return "exito";
	}
}
