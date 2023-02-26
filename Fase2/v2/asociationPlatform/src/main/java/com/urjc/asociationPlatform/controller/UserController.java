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

	User currentUser;

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

	    Principal principal = request.getUserPrincipal();

	 	if(principal != null) {
            userService.findByUsername(principal.getName()).ifPresent(u -> currentUser = u);
            model.addAttribute("currentuser", currentUser);
	 	}
	}
    
    @GetMapping("/admin/editarUsuarios")
    public String listaUsuarios(Model model){
        List<User> userlist = userService.findAll();
        model.addAttribute("userlist", userlist);
        return "users"; 
    }
	
    @GetMapping("/admin/editarUsuarios/{id}")
	public String obtainUser(Model model, @PathVariable long id) {
		try { 

			User user = userService.findById(id).orElseThrow();
			model.addAttribute("user", user);

			return "edituser";

		} catch (Exception e) {
			e.printStackTrace();
			return "/error"; 
		}
	}

	@PostMapping("/admin/editarUsuarios/{id}")
	public String editProfile(Model model, User newUser,@PathVariable long id){ 
		try { User user = userService.findById(id).orElseThrow();

			user.setUsername(newUser.getUsername());
			user.setEmail(newUser.getEmail());
			user.setRol(newUser.getRol());
			userService.save(user);

			return "redirect:/admin/editarUsuarios";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error"; 
		}
	}

	@PostMapping("/admin/editarUsuarios/{id}/delete")
	public String deleteProfile(Model model, User newUser,@PathVariable long id){

		userService.deleteById(id);

		return "redirect:/admin/editarUsuarios";
	}

}
