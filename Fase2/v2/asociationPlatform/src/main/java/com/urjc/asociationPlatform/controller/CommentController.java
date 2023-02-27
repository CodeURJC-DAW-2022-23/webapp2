package com.urjc.asociationPlatform.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.service.CommentService;

public class CommentController {
    @Autowired
    private CommentService comentService;

    @PostMapping("/admin/editarComentarios/{id}")
	public String addComment(Model model, Comment newComent, @PathVariable long id){
		comentService.save(newComent);
		return "redirect:/";
	}

    @PostMapping("/admin/editarComentarios/{id}/delete")
	public String deleteComment(Model model, Comment newComent,@PathVariable long id){
		comentService.deleteById(id);
		return "redirect:/";
	}

    public String actualDateTime(){
        LocalDateTime fecha = LocalDateTime.now();
        
        return fecha.toString();
    }
}
