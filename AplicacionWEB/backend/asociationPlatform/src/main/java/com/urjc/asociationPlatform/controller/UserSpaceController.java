package com.urjc.asociationPlatform.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.UserService;

import java.security.Principal;

@Controller
public class UserSpaceController {

    @Autowired
    private UserService userService;

    User currentUser;

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

	    Principal principal = request.getUserPrincipal();

	 	if(principal != null) {
            userService.findByUsername(principal.getName()).ifPresent(u -> currentUser = u);
            model.addAttribute("user", currentUser);
	 	}
	}

    @GetMapping("/miCuenta")
	public String micuenta(Model model) {
		return "myAccount";
	}

    @PostMapping("/miCuenta/{id}")
	public String editProfile(Model model, User newUser, @PathVariable long id){
	 	try {
            User user = userService.findById(id).orElseThrow();

            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            userService.save(user);

            return "myAccount";
	 		
         } catch (Exception e) {
             return "404";
         }
	}
}
    

