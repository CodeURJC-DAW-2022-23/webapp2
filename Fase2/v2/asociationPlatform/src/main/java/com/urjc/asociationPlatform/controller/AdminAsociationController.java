package com.urjc.asociationPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.service.AsociationService;

@Controller
public class AdminAsociationController {

    @Autowired
    AsociationService asoService;

    @GetMapping("/editAsoc")
    public String listAsociations(Model model){
        List<Asociation> list = asoService.findAll();
        model.addAttribute("asocList", list);
        return "asociations"; 
    }

    /*
    @GetMapping("/editarUsuarios/{id}")
	public String obtainUser(Model model, @PathVariable long id) {

		User user = userService.findById(id);

		model.addAttribute("user", user);

		return "";
	}


    @GetMapping("/editarUsuario/{id}")
	public String obtainUser(Model model, @PathVariable long id) {
		User user = userService.findById(id);
		if (user.getId().equals(userService.getId())) {
			model.addAttribute("user", user);
			return "";
		}
		return "redirect:/404";
	}

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
