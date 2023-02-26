package com.urjc.asociationPlatform.controller;

import java.security.Principal;
import java.util.*;

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
    private String[] monthsValue={"", "All", "ENERO", "FEBRERO", "MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
    private String[] monthsContent={"Mes","Todos", "Enero", "Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    private String campus= "ALCORCON";
    private String[] campusValue={"", "All","ALCORCON","ARANJUEZ","FUENLABRADA","MOSTOLES","MADRID-VICALVARO","MADRID-QUINTANA"};
    private String[] campusContent={"Campus","Todos","Alcorcón","Aranjuez","Fuenlabrada","Móstoles","Madrid-Vicalvaro","Madrid-Quintana"};

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
        //searchInfo="";
        //asociation = "";
        //month = "";
        //campus= "";
        model.addAttribute("eventos",eventService.getEventsByFilters(searchInfo, month, campus, asociation));
        generateFiltersOptions(model);
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
        generateFiltersOptions(model);
        return "redirect:/";
    }
    //options
    @PostMapping("/asociationOption")
    public String asociationOption(Model model, @RequestParam String asociationValue){
        asociation=asociationValue;
        model.addAttribute("eventos",eventService.getEventsByFilters(searchInfo, month, campus, asociation));
        generateFiltersOptions(model);
        return "redirect:/";
    }
    @PostMapping("/monthOption")
    public String monthOption(Model model, @RequestParam String monthSelect){
        month=monthSelect;
        model.addAttribute("eventos",eventService.getEventsByFilters(searchInfo, month, campus, asociation));
        generateFiltersOptions(model);
        return "redirect:/";
    }
    @PostMapping("/campusOption")
    public String campusOption(Model model, @RequestParam String campusValue){
        campus=campusValue;
        model.addAttribute("eventos",eventService.getEventsByFilters(searchInfo, month, campus, asociation));
        generateFiltersOptions(model);
        return "redirect:/";
    }

    public void generateFiltersOptions(Model model){
        List<Map<String, Object>> months = new ArrayList<>();
        for(int i=0;i<monthsValue.length;i++){
            Map<String, Object> option = new HashMap<>();
            option.put("value", monthsValue[i].toString());
            option.put("selected", monthsValue[i].equals(month));
            option.put("content", monthsContent[i]);
            months.add(option);
        }
        model.addAttribute("monthsValues", months);


        List<Map<String, Object>> campusL = new ArrayList<>();
        for(int i=0;i<campusValue.length;i++){
            Map<String, Object> option = new HashMap<>();
            option.put("value", campusValue[i].toString());
            option.put("selected", campusValue[i].equals(campus));
            option.put("content", campusContent[i]);
            campusL.add(option);
        }
        model.addAttribute("campusValues", campusL);

        model.addAttribute("seachBarContent", searchInfo);
    }
}
