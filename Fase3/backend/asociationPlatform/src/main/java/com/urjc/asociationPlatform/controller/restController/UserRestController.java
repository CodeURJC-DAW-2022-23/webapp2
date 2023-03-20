package com.urjc.asociationPlatform.controller.restController;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
	private UserService userService;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    /*@GetMapping("/{id}")
	public ResponseEntity<User> getProfile(@PathVariable long id) {
        
        if(userService.findById(id).isPresent()){
            User user = userService.findById(id).get();
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/me")
    public ResponseEntity<User> me(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if(principal != null){
            User user = userService.findByUsername(principal.getName()).get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    //modify user

    //delete user
}
