/* 
package com.urjc.asociationPlatform.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.model.Evento;

import antlr.collections.List;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;
 
    
    @GetMapping("/editarEventos")
    public String listaEventos(Model model){
        List<Evento> lista = eventService.findAll();
        model.addAttribute("lista", lista);
        return "events"; 
    }

    @GetMapping("/editarEventos/{id}")
	public String obtainEvent(Model model, @PathVariable long id) {

		Evento event = eventService.findById(id);

		model.addAttribute("event", event);

		return "";
	}


    @GetMapping("/editarEvento/{id}")
	public String obtainEvent(Model model, @PathVariable long id) {
		Evento event = eventService.findById(id);
		if (event.getId().equals(eventService.getId())) {
			model.addAttribute("event", event);
			return "";
		}
		return "redirect:/404";
	}

	@PostMapping("/editarEvento")
	public String editEvent(Model model, Evento newEvent) throws IOException, SQLException {
		eventService.setName(newEvent.getName());
		eventService.setDate(newEvent.getDate());
        eventService.setDescription(newEvent.getDescription());
		eventService.setAso(newEvent.getAso());
        eventService.setImage(newEvent.getImage());
        eventService.setPlace(newEvent.getPlace());
        eventService.setCapacity(newEvent.getCapacity());
        eventService.setReservations(newEvent.getReservations());

        
		eventService.save(eventService);
		return "redirect:/";
	}

}
*/