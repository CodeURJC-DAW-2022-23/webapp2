package com.urjc.asociationPlatform.controller.restController;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @GetMapping("/{id}")
	public ResponseEntity<User> getProfile(@PathVariable long id,HttpServletRequest request) {
        if(userService.findById(id).isPresent()){
            User user = userService.findById(id).get();
            try{
                User userPrincipal = userService.findByUsername(request.getUserPrincipal().getName()).orElseThrow();
                if (user.getRol().equals("ADMIN")) {
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
                else
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }catch (NoSuchElementException e){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
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
    }

    //modify my user
    @PatchMapping("/me")
    public ResponseEntity<User> modifyUser(@RequestParam String newName, @RequestParam String newEmail, HttpServletRequest request) throws IOException, SQLException {
            try{
                User userPrincipal = userService.findByUsername(request.getUserPrincipal().getName()).orElseThrow();
                userPrincipal.setUsername(newName);
                userPrincipal.setEmail(newEmail);
                userService.save(userPrincipal);
                return new ResponseEntity<>(userPrincipal, HttpStatus.OK);
            }catch (NoSuchElementException e){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
    }

    //admin modify
    @PatchMapping("/admin/{id}")
    public ResponseEntity<User> modifyUserbyAdmin(@PathVariable long id, @RequestParam String newName, @RequestParam String newEmail, @RequestParam String newRol, HttpServletRequest request) throws IOException, SQLException {
        if(userService.findById(id).isPresent()){
            User user = userService.findById(id).get();
            try{
                User userPrincipal = userService.findByUsername(request.getUserPrincipal().getName()).orElseThrow();
                if (user.getRol().equals("ADMIN")) {
                    user.setUsername(newName);
                    user.setEmail(newEmail);
                    user.setRol(newRol);
                    userService.save(user);
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
                else
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }catch (NoSuchElementException e){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //register
    @PostMapping("/")
    public ResponseEntity<User> register(@RequestParam String email,@RequestParam String name,@RequestParam String password,@RequestParam String rol){
        if(!userService.existUsername(name) && !userService.existEmail(email)){
            User user;
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    //change my password

    //delete user
}
