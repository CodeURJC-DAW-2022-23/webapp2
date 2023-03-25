package com.urjc.asociationPlatform.controller.restController;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.urjc.asociationPlatform.email.EmailDetails;
import com.urjc.asociationPlatform.email.EmailServiceImpl;
import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.model.restModel.ChangePassword;
import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.CommentService;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

    @Operation(summary = "Get a user by admin")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "user found",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),        
        @ApiResponse(responseCode = "400", description = "invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "user is not registered", content = @Content),
        @ApiResponse(responseCode = "404", description = "user not found", content = @Content)
            
    })
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

    @Operation(summary = "Get current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "user found",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
        @ApiResponse(responseCode = "401", description = "user is not registered", content = @Content)
            
    })
    @GetMapping("/me")
    public ResponseEntity<User> me(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if(principal != null){
            User user = userService.findByUsername(principal.getName()).get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Operation(summary = "Modify current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "user modified sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
        @ApiResponse(responseCode = "401", description = "user is not registered", content = @Content)
            
    })
    @PatchMapping("/me")
    public ResponseEntity<User> modifyUser(@RequestParam String newName, @RequestParam String newEmail, HttpServletRequest request) throws IOException, SQLException {
        Principal principal = request.getUserPrincipal();
        if(principal != null){
            User userPrincipal = userService.findByUsername(request.getUserPrincipal().getName()).orElseThrow();
            userPrincipal.setUsername(newName);
            userPrincipal.setEmail(newEmail);
            userService.save(userPrincipal);
            return new ResponseEntity<>(userPrincipal, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
     
    }

    @Operation(summary = "Modify user by admin")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "user modified sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
        @ApiResponse(responseCode = "400", description = "invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "user is not registered", content = @Content),
        @ApiResponse(responseCode = "403", description = "not enough privileges or admin is modifying itself", content = @Content),
        @ApiResponse(responseCode = "404", description = "user not found", content = @Content)
            
    })
    @PatchMapping("/admin/{id}")
    public ResponseEntity<User> modifyUserbyAdmin(@PathVariable long id, @RequestParam String newName, @RequestParam String newEmail, @RequestParam String newRol, HttpServletRequest request) throws IOException, SQLException {
        if(userService.findById(id).isPresent()){
            User user = userService.findById(id).get();
            try{
                User userPrincipal = userService.findByUsername(request.getUserPrincipal().getName()).orElseThrow();
                if (userPrincipal.getId() != user.getId()) {
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

    @Operation(summary = "Register a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "user created sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
        @ApiResponse(responseCode = "400", description = "invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "403", description = "existing user or wrong role", content = @Content)
            
    })
    @PostMapping("/")
    public ResponseEntity<User> register(@RequestBody User user){
        if(!userService.existUsername(user.getUsername()) && !userService.existEmail(user.getEmail())){
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
            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/admin/{id}")
                .buildAndExpand(user.getId())
                .toUri();
                return ResponseEntity.created(location).build(); 
        }
        else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Operation(summary = "Modify my password")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "password modified sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
        @ApiResponse(responseCode = "401", description = "user is not registered", content = @Content),
        @ApiResponse(responseCode = "403", description = "wrong password", content = @Content)
            
    })
    @PatchMapping("/me/password")
    public ResponseEntity<User> modifyMyPassWord(@RequestBody ChangePassword password, HttpServletRequest request) throws IOException, SQLException {
        Principal principal = request.getUserPrincipal();
        if(principal != null){
            User userPrincipal = userService.findByUsername(request.getUserPrincipal().getName()).orElseThrow();
            if(passwordEncoder.matches(password.oldPassword,userPrincipal.getencodedPassword())){
                userPrincipal.setencodedPassword(passwordEncoder.encode(password.newPassword));
                userService.save(userPrincipal);
                return new ResponseEntity<>(userPrincipal, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Operation(summary = "Delete user by admin")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "user deleted sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
        @ApiResponse(responseCode = "400", description = "invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "user is not registered", content = @Content),
        @ApiResponse(responseCode = "403", description = "not enough privileges or admin is deleting itself", content = @Content),
        @ApiResponse(responseCode = "404", description = "user not found", content = @Content)
            
    })
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id,HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        if(principal != null){
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

                    return new ResponseEntity<>(user,HttpStatus.OK);
                }
                else
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }catch (NoSuchElementException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /*@GetMapping("/me/favorites")
    private ResponseEntity<List<Event>> getFavorites(HttpServletRequest request){
        if(request.getUserPrincipal() != null){
                User userPrincipal = userService.findByUsername(request.getUserPrincipal().getName()).orElseThrow();
                List<Event> favs = userPrincipal.getFavoritos();
                return new ResponseEntity<>(favs,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }*/

    @Operation(summary = "Add event to current user's favorites")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "event added sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class))}),
        @ApiResponse(responseCode = "400", description = "invalid event id supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "user is not registered", content = @Content),
        @ApiResponse(responseCode = "404", description = "event not found", content = @Content)
            
    })
    private ResponseEntity<Event> addFavorites(@PathVariable long id, HttpServletRequest request){
        if(request.getUserPrincipal() != null){
                User userPrincipal = userService.findByUsername(request.getUserPrincipal().getName()).orElseThrow();
                try{
                    Event event = eventService.findById(id).orElseThrow();
                    userPrincipal.addFavoritos(event);
                    userService.save(userPrincipal);
                    return new ResponseEntity<>(event,HttpStatus.OK);
                }
                catch (NoSuchElementException e){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                
        }
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Operation(summary = "Remove event from current user's favorites")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "event removed sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class))}),
        @ApiResponse(responseCode = "400", description = "invalid event id supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "user is not registered", content = @Content),
        @ApiResponse(responseCode = "404", description = "event not found", content = @Content)
            
    })
    @DeleteMapping("/me/favorites/{id}")
    private ResponseEntity<Event> removeFavorites(@PathVariable long id, HttpServletRequest request){
        if(request.getUserPrincipal() != null){
                User userPrincipal = userService.findByUsername(request.getUserPrincipal().getName()).orElseThrow();
                try{
                    Event event = eventService.findById(id).orElseThrow();
                    userPrincipal.removeFavoritos(event);
                    userService.save(userPrincipal);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                catch (NoSuchElementException e){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                
        }
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
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
