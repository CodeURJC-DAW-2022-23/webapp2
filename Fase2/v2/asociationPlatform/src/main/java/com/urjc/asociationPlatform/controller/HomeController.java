package com.urjc.asociationPlatform.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.urjc.asociationPlatform.service.UserService;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

	    Principal principal = request.getUserPrincipal();

	 	if(principal != null) {
	 		model.addAttribute("logged", true);

             if(request.isUserInRole("ASO")){
                 model.addAttribute("aso",true);
             }else if(request.isUserInRole("BASE")){
                 model.addAttribute("base",true);
            }
			//model.addAttribute("admin", request.isUserInRole("ADMIN"));
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

    @RequestMapping("/miEspacio")
	public String miespacio() {
		return "myAso";
	}

    @RequestMapping("/crearEvento")
	public String prueba() {
		return "crearEvento";
	}


}
