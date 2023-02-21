package com.urjc.asociationPlatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.service.AsociationService;

@Controller
public class AdminAsociationController {

    @Autowired
    AsociationService asoService;

    @GetMapping("/adminAsoc")
    public String listAsociations(Model model){
        List<Asociation> list = asoService.findAll();
        model.addAttribute("asocList", list);
        return "asociations"; 
    }
    
    @GetMapping("/editAsociations/{id}")
	public String obtainAsoc(Model model, @PathVariable long id) {

		Optional<Asociation> asoc = asoService.findById(id);

		model.addAttribute("asociation", asoc);

		return "editAsociations";
	}

/* 
	@PostMapping("/editarUsuario")
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
