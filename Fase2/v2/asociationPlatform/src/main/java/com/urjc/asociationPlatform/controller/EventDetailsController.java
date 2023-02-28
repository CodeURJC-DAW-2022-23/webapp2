package com.urjc.asociationPlatform.controller;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.CommentService;
import com.urjc.asociationPlatform.service.EventService;


@Controller
public class EventDetailsController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/infoEvento/{id}")
    public String infoEvento(Model model, @PathVariable long id){

        Event event = eventService.findById(id).orElseThrow();

        model.addAttribute("event", event);

        List<Comment> commentsList = commentService.findAll();
        
        model.addAttribute("commentsList", commentsList);

        return "detalles";
    }

    
}
