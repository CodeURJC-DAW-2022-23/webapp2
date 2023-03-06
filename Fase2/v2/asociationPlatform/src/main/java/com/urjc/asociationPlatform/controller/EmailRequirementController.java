package com.urjc.asociationPlatform.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.UserService;

@Controller
public class EmailRequirementController {
    @Autowired
    AsociationService asoService;

    @Autowired
    UserService userService;

    @Autowired
	private PasswordEncoder passwordEncoder;

    User currentUser;

    @GetMapping("/newAso")
    public String newAsociation(){
        return "createAso";
    }

    @PostMapping("/newAso/{email}")
    public String createAso(Model model, Asociation aso, @PathVariable String email) {

        asoService.save(aso);

        User user = userService.findByEmail(email).orElseThrow();
        
        aso.setOwner(user);
        user.setValidated(true);
        user.setCheckToken(null);

        userService.save(user);
        
        return "redirect:/";
    }
}
