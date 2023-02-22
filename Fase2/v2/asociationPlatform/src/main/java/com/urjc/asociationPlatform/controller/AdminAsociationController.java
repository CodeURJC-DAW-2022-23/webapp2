package com.urjc.asociationPlatform.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
		/* 
		Optional<Asociation> asoc = asoService.findById(id);
		if(asoc.isPresent() == false)
			return "404";
		else{
			model.addAttribute("asociation", asoc);
			return "editAsociations";
		}*/
		try { Asociation asoc = asoService.findById(id).orElseThrow();
			model.addAttribute("asociation", asoc);
			return "editAsociations";
        } catch (Exception e) {
            return "404";
        }
	}

	/* 
	@PostMapping("/editAsociations")
	public String editProfile(Model model, Asociation asoc) throws IOException, SQLException {
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
