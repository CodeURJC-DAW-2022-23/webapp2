package com.urjc.asociationPlatform.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.UserService;

@Controller
public class UserAsociationController {

	@Autowired
    private UserService userService;

	User currentUser;

    @GetMapping("/asocConfig")
	public String acessAsoc(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();
		if(principal != null){
			userService.findByEmail(principal.getName()).ifPresent(u -> currentUser = u);
			if(currentUser.getRol() == "aso"){
				model.addAttribute("asociation", currentUser.getAsociation());
				return "editAsociations";
			}
			else{
				return "404";
			}
		}
		else{
			return "404";
		}
	}
}
