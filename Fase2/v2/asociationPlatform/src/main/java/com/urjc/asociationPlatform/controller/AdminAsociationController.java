package com.urjc.asociationPlatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminAsociationController {
    @GetMapping ("/adminAsoc")
    public String adminAsoc(Model model){
        return "asociations";
    }
}
