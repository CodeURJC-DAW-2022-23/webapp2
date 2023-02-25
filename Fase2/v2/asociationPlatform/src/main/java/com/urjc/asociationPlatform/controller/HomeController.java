package com.urjc.asociationPlatform.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;

@Controller
public class HomeController {

    private String searchInfo="";//the value "" indicates that match with all the coincidences
    private String asociation = "";
    private String month = "";
    private String campus= "";

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

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
        searchInfo="";
        asociation = "";
        month = "";
        campus= "";
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
    //seach bar
    @PostMapping("/searchCoincidence")
    public String searchCoincidence(Model model, @RequestParam String searchBar){
        searchInfo=searchBar;
        model.addAttribute("eventos",eventService.getEventsByFilters(searchInfo, month, campus, asociation));
        return "redirect:/";
    }
    //options
    @PostMapping("/asociationOption")
    public String asociationOption(Model model, @RequestParam String asociationValue){
        asociation=asociationValue;
        model.addAttribute("eventos",eventService.getEventsByFilters(searchInfo, month, campus, asociation));
        return "home";
    }
    @PostMapping("/monthOption")
    public String monthOption(Model model, @RequestParam String monthValue){
        month=monthValue;
        model.addAttribute("eventos",eventService.getEventsByFilters(searchInfo, month, campus, asociation));
        return "home";
    }
    @PostMapping("/campusOption")
    public String campusOption(Model model, @RequestParam String campusValue){
        campus=campusValue;
        model.addAttribute("eventos",eventService.getEventsByFilters(searchInfo, month, campus, asociation));
        return "home";
    }


}
