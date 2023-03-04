package com.urjc.asociationPlatform.controller;

import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;

@Controller
public class HomeController {

    private String searchInfo="";//the value "" indicates that match with all the coincidences
    private String asociation = "";
    private String month = "";
    private String[] monthsValue={"", "All", "ENERO", "FEBRERO", "MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
    private String[] monthsContent={"Mes","Todos", "Enero", "Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    private String campus= "";
    private String[] campusValue={"", "All","ALCORCON","ARANJUEZ","FUENLABRADA","MOSTOLES","MADRID-VICALVARO","MADRID-QUINTANA"};
    private String[] campusContent={"Campus","Todos","Alcorcón","Aranjuez","Fuenlabrada","Móstoles","Madrid-Vicalvaro","Madrid-Quintana"};

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AsociationService asociationService;

    User currentUser;
    private List<Asociation> asociations;
    private List<String> asoValues=new ArrayList<>();
    private List<String> asoContenet=new ArrayList<>();

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

	    Principal principal = request.getUserPrincipal();

        List<Event> eventList = eventService.findAll();

        model.addAttribute("eventList", eventList);

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
        //searchInfo="";
        //asociation = "";
        //month = "";
        //campus= "";
        List<Event> events=eventService.getEventsByFilters(searchInfo, month, campus, asociation);
        model.addAttribute("events",events);
        if(asociations==null){
            asociations = asociationService.findAll();
            System.out.println("Numero de asociaciones: "+asociations.size());
            asoValues.add("");
            asoValues.add("All");
            asoContenet.add("Asociación");
            asoContenet.add("Todas");
            for(int i=0;i<asociations.size();i++){
                asoValues.add(asociations.get(i).getName());
                asoContenet.add(asociations.get(i).getName());
            }
        }
        

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
    @PostMapping("/globalFormSubmit") 
    public String globalFormSubmit(Model model, @RequestParam Map<String,String> formData){
        if(formData.containsKey("searchBar")){
            searchInfo=formData.get("searchBar");
        }
        if(formData.containsKey("monthSelect")){
            month=formData.get("monthSelect");
        }
        if(formData.containsKey("campusValue")){
            campus=formData.get("campusValue");
        }
        if(formData.containsKey("asociationValue")){
            asociation=formData.get("asociationValue");
        }

        model.addAttribute("events",eventService.getEventsByFilters(searchInfo, month, campus, asociation));
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

        List<Map<String, Object>> asoL = new ArrayList<>();
        for(int i=0;i<asoValues.size();i++){
            Map<String, Object> option = new HashMap<>();
            option.put("value", asoValues.get(i).toString());
            option.put("selected", asoValues.get(i).equals(asociation));
            option.put("content", asoContenet.get(i));
            asoL.add(option);
        }
        model.addAttribute("asoValues", asoL);

        model.addAttribute("seachBarContent", searchInfo);
    }
}
