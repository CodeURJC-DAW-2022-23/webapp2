package com.urjc.asociationPlatform.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.UserService;

import java.security.Principal;

@Controller
public class AsociationUserController {

    @Autowired
    AsociationService asoService;

    @Autowired
    private UserService userService;

    User currentUser;

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

	    Principal principal = request.getUserPrincipal();

	 	if(principal != null) {
            userService.findByUsername(principal.getName()).ifPresent(u -> currentUser = u);
            model.addAttribute("asociation", currentUser.getAsociation());
	 	}
	}

    @GetMapping("/aso/miEspacio")
	public String miespacio(Model model) {
		return "myAso";
	}

    @GetMapping("editAsoc/{id}")
	public String obtainAsociation(Model model, @PathVariable long id, HttpServletRequest request) {

		try { Asociation asoc = asoService.findById(id).orElseThrow();
			model.addAttribute("asociation", asoc);
			return "editAsociations";
		} catch (Exception e) {
            return "404";
        }
	}

    @PostMapping("editAsoc/{id}")
	public String editProfile(Model model, Asociation newAsoc, @PathVariable long id){
		try { asoService.findById(id).orElseThrow();
			String result;
            result = "myAso";
			if(newAsoc.getCampus().trim().isEmpty() || newAsoc.getFaculty().trim().isEmpty() || newAsoc.getName().trim().isEmpty()){
				return result;
			}
			else{
				newAsoc.setId(id);
				asoService.save(newAsoc);
				return result;
			}
        } catch (Exception e) {
            return "redirect:/404";
        }
	}
}