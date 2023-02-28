package com.urjc.asociationPlatform.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.service.CommentService;
import com.urjc.asociationPlatform.service.EventService;

public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
	private EventService eventService;

    @PostMapping("/crearComentario/{id}")
	public String addComment(Model model, Comment newComent, @PathVariable long id, HttpServletRequest request){

        Principal principal = request.getUserPrincipal();

        if(principal != null){
            commentService.save(newComent);
            
            Event event = eventService.findById(id).orElseThrow();
            event.addComment(newComent);
            eventService.save(event);
            return "detalles";
        } else {
            return "/login";
        } 
		
	}

    @PostMapping("/admin/editarComentarios/{id}/delete")
	public String deleteComment(Model model, Comment newComent,@PathVariable long id){
		commentService.deleteById(id);
		return "redirect:/";
	}

    public String actualDateTime(){
        LocalDateTime fecha = LocalDateTime.now();
        
        return fecha.toString();
    }
}
