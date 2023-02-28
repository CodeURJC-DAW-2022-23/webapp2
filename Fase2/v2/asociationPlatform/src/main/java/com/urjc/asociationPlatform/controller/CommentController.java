package com.urjc.asociationPlatform.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.service.CommentService;
import com.urjc.asociationPlatform.service.EventService;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
	private EventService eventService;

    @PostMapping("/crearComentario/{id}")
	public String addComment(Model model, Comment newComent, @PathVariable long id, HttpServletRequest request){

        
        Principal principal = request.getUserPrincipal();

        if(principal != null){
            String fecha = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")
                    .format(LocalDateTime.now());
            newComent.setTime(fecha.toString());

            newComent.setCommentUser(principal.getName());
            
            commentService.save(newComent);
            
            Event event = eventService.findById(id).orElseThrow();
            System.out.println(event.getId(id));
            event.addComment(newComent);
            eventService.save(event);

            return "redirect:/infoEvento/{id}";
        } else {
            return "/login";
        } 
		
	}

    @PostMapping("/admin/editarComentarios/{id}/delete")
	public String deleteComment(Model model, Comment newComent,@PathVariable long id){
		commentService.deleteById(id);
		return "redirect:/";
	}
}
