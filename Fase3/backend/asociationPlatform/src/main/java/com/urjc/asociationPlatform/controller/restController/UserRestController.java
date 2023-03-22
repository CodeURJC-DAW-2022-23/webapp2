package com.urjc.asociationPlatform.controller.restController;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.urjc.asociationPlatform.email.EmailDetails;
import com.urjc.asociationPlatform.email.EmailServiceImpl;
import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.CommentService;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
	private UserService userService;

    @Autowired
	private EmailServiceImpl emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private EventService eventService;

    @Autowired
    private AsociationService asoService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/admin/{id}")
	public ResponseEntity<User> getProfile(@PathVariable long id,HttpServletRequest request) {
        if(userService.findById(id).isPresent()){
            User user = userService.findById(id).get();
            try{
                User userPrincipal = userService.findByUsername(request.getUserPrincipal().getName()).orElseThrow();
                return new ResponseEntity<>(user, HttpStatus.OK);
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
                if (userPrincipal != user) {
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
            User user = new User(email,name,passwordEncoder.encode(password));
            user.setRol(rol);
            if(user.getRol().equals("ASO")){

                EmailDetails emailDetails = new EmailDetails();
                emailDetails.adminMode(user.getUsername(), user.getEmail());

                emailService.sendSimpleMail(emailDetails);
            }else if(user.getRol().equals("BASE")){

                user.setValidated(true);
            }
            else{
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    //change my password
    @PatchMapping("/me/password")
    public ResponseEntity<User> modifyMyPassWord(@RequestParam String oldPassword, @RequestParam String newPassword, HttpServletRequest request) throws IOException, SQLException {
        try{
            User userPrincipal = userService.findByUsername(request.getUserPrincipal().getName()).orElseThrow();
            if(passwordEncoder.matches(userPrincipal.getencodedPassword(), oldPassword)){
                userPrincipal.setencodedPassword(passwordEncoder.encode(newPassword));
                userService.save(userPrincipal);
                return new ResponseEntity<>(userPrincipal, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    //delete user
    @DeleteMapping("/admin/{id}/delete")
    public ResponseEntity<User> deleteUser(@PathVariable long id,HttpServletRequest request){
        try{
            User userPrincipal = userService.findByUsername(request.getUserPrincipal().getName()).orElseThrow();
            try{
                User user = userService.findById(id).orElseThrow();
                if(user.getId() != userPrincipal.getId()){
                    List<Event> events= eventService.findAll();
                    for(Event event:events){
                        List<Comment> eventComments = event.deleteUserReferences(user);
                        for(Comment comment: eventComments){
                            commentService.save(comment);
                        }
                    }
                    if(user.getRol().equals("ASO")){
                        Asociation asociation = asoService.findByOwner(user).orElseThrow();
                        List<Event> asoEvents= eventService.findAllbyAsociation(asociation);
                        for(Event asoEvent : asoEvents){
                            System.out.print("\n borrando evento \n");
                            asoEvent = clearEvent(asoEvent);
                            eventService.deleteById(asoEvent.getId());
                        }
                        System.out.print("\n borrando asociacion \n");
                        asoService.deleteById(asociation.getId());
                    }
                    else{
                        userService.deleteById(id);
                    }

                    return new ResponseEntity<>(HttpStatus.OK);
                }
                else
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }catch (NoSuchElementException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private Event clearEvent(Event event){
		List<User> users = userService.findAll();
		for(User user:users){
		  if(user.isInFavorites(event)){
			user.removeFavoritos(event);
			userService.save(user);
		  }
			
		}
		List<Comment> comments=event.getComments();
		for(Comment comment:comments){
		  comment.clear();
		  commentService.save(comment);
		  commentService.deleteById(comment.getId());
		}
		event.clear();
		eventService.save(event);
		return event;
	}
}
