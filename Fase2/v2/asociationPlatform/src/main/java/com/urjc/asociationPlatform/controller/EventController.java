
package com.urjc.asociationPlatform.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;

import java.security.Principal;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;

	@Autowired
    private UserService userService;

    User currentUser;

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

	    Principal principal = request.getUserPrincipal();

	 	if(principal != null) {
            userService.findByUsername(principal.getName()).ifPresent(u -> currentUser = u);
            model.addAttribute("user", currentUser);
	 	}
	}
 
    
    @GetMapping("/admin/editarEventos")
    public String listaEventos(Model model){
        List<Event> eventlist = eventService.findAll();
        model.addAttribute("eventlist", eventlist);
        return "events"; 
    }


    @GetMapping("/admin/editarEventos/{id}")
	public String obtainEvent(Model model, @PathVariable long id) {
		Event event = eventService.findById(id).orElseThrow();

		model.addAttribute("event", event);

		return "editevent";
	}

	@PostMapping("/admin/editarEventos/{id}")
	public String editEvents(Model model, Event newEvent,@PathVariable long id){ 
		try { Event event = eventService.findById(id).orElseThrow();
			
			event.setName(newEvent.getName());
			event.setDate(newEvent.getDate());
			event.setLocation(newEvent.getLocation());
			event.setAsociation(newEvent.getAsociation());
			eventService.save(event);

			return "redirect:/admin/editarEventos";
		} catch (Exception e) {
			e.printStackTrace();
			return "404"; 
		}
	}

	@PostMapping("/admin/editarEventos/{id}/delete")
	public String deleteProfile(Model model, Event newEvent,@PathVariable long id){

		eventService.deleteById(id);

		return "redirect:/admin/editarEventos";
	}
}
