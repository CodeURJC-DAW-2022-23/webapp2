package com.urjc.asociationPlatform.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.urjc.asociationPlatform.service.UserService;
import com.urjc.asociationPlatform.model.User;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/editarUsuarios")
    public String listaUsuarios(Model model){
        List<User> userlist = userService.findAll();
        model.addAttribute("userlist", userlist);
        return "users"; 
    }
	
    @GetMapping("/editarUsuarios/{id}")
	public String obtainUser(Model model, @PathVariable long id) {

		Optional<User> user = userService.findById(id);

		model.addAttribute("user", user);

		return "edituser";
	}


	@PutMapping("/editarUsuarios/{id}")
	public String editProfile(Model model, User newUser,@PathVariable long id){

		newUser.setId(id);
		userService.save(newUser);
		return "redirect:/";
	}

}
