package com.urjc.asociationPlatform.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;

import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;

@Controller
public class EventManagerController{

	@Autowired
  private EventService eventService;

	@Autowired
    private UserService userService;

		User currentUser;
		
  @GetMapping("/aso/eventManagerAso")   
	public String miespacio(Model model, HttpServletRequest request) {
		String association = " ";

    Principal principal = request.getUserPrincipal();

	 	if(principal != null) {
	 		userService.findByUsername(principal.getName()).ifPresent(u -> currentUser = u);
      if(currentUser!=null){
        association = currentUser.getAsociation().getName();
      }   
	 	} 
		model.addAttribute("eventlist", eventService.findAllbyAsociation(association));

		return "eventManager";
	}
}