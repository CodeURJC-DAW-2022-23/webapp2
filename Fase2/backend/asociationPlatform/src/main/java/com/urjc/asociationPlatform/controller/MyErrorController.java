package com.urjc.asociationPlatform.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyErrorController implements ErrorController{
	
    @GetMapping("/error")
	public String error(Model model) {
		return "404";
	}
}
