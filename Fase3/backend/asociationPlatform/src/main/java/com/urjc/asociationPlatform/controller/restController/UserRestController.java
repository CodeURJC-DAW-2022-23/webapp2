package com.urjc.asociationPlatform.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urjc.asociationPlatform.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
	private UserService userService;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    public void test(){
        
    }
}
