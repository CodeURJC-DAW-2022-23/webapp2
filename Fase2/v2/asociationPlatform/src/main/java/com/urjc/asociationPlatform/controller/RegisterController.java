package com.urjc.asociationPlatform.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.urjc.asociationPlatform.email.EmailDetails;
import com.urjc.asociationPlatform.email.EmailServiceImpl;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.UserService;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
	private EmailServiceImpl emailService;

    User currentUser;

    private String correo = "federicogarciaagapito@gmail.com";

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

	    Principal principal = request.getUserPrincipal();

	 	if(principal != null) {
	 		userService.findByEmail(principal.getName()).ifPresent(u -> currentUser = u);
	 		model.addAttribute("logged", true);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
	 	} else {
	 		model.addAttribute("logged", false);
		}
	}

    @PostMapping("/register")
    public String register(Model model, User user){
        if(!userService.existEmail(user.getEmail())){
            user.setencodedPassword(passwordEncoder.encode(user.getencodedPassword()));
            System.out.println(user.getRol());
            if(user.getRol().equals("ASO")){
                EmailDetails emailDetails = new EmailDetails();
                emailDetails.adminMode(user.getUsername(), user.getEmail());

                emailService.sendSimpleMail(emailDetails);
            }else{
                userService.save(user);
            }
            return "redirect:/login"; 
        }else{
            return "logerror";    
        }      
    }

    @PostMapping("/accepted/{email}")
    public String accepted(Model model,@PathVariable String email){
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.acceptedMode(email);
        emailService.sendSimpleMail(emailDetails);
        return "redirect:/";
    }

    @PostMapping("/rejected/{email}")
    public String rejected(Model model,@PathVariable String email){
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.rejectedMode(email);
        emailService.sendSimpleMail(emailDetails);
        return "redirect:/";
    }
}
