
package com.urjc.asociationPlatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.model.Event;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;
 
    
    @GetMapping("/editarEventos")
    public String listaEventos(Model model){
        List<Event> eventlist = eventService.findAll();
        model.addAttribute("eventlist", eventlist);
        return "events"; 
    }


    @GetMapping("/editarEventos/{id}")
	public String obtainEvent(Model model, @PathVariable long id) {
		Optional<Event> event = eventService.findById(id);

		model.addAttribute("event", event);

		return "editevent";
	}

	@PostMapping("/editarEventos/{id}")
	public String editEvents(Model model, Event newEvent,@PathVariable long id){ 
		try { Event event = eventService.findById(id).orElseThrow();
			
			event.setName(newEvent.getName());
			event.setDate(newEvent.getDate());
			event.setLocation(newEvent.getLocation());
			event.setAsociation(newEvent.getAsociation());
			eventService.save(event);

			return "redirect:/editarUsuarios";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error"; 
		}
	}

	@PostMapping("/editarEventos/{id}/delete")
	public String deleteProfile(Model model, Event newEvent,@PathVariable long id){

		eventService.deleteById(id);

		return "redirect:/";
	}
}
