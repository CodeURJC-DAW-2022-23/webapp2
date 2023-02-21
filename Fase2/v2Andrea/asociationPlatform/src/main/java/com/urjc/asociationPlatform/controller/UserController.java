package com.urjc.asociationPlatform.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

	/* 
	@PostMapping("/editarUsuarios/{id}")
	public String editProfile(Model model, User newUser) throws IOException, SQLException {
		userService.setUsername(newUser.getUsername());
		newUser.getEncodedPassword();
		if (!newUser.getEncodedPassword().equals("")){
			userService.setEncodedPassword(passwordEncoder.encode(newUser.getEncodedPassword()));
		}
		userService.setRol(newUser.getRol());
        userService.setAsoname(newUser.getAsoname());
		userService.save(userService);
		return "redirect:/";
	}*/

}
