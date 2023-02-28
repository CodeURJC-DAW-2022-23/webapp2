package com.urjc.asociationPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


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

    User currentUser;

    @GetMapping("/newAso")
    public String newAsociation(){
        return "createAso";
    }

    @PostMapping("/newAso")
    public String createAso(Model model, Asociation aso) {
        asoService.save(aso);

        return "myAso";
    }
}
