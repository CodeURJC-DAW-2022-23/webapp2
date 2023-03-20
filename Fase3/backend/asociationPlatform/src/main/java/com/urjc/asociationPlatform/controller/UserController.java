package com.urjc.asociationPlatform.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.CommentService;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;
import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
	@Autowired
	private AsociationService asoService;
	@Autowired
	private EventService eventService;
	@Autowired
	private CommentService commentService;

	User currentUser;

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

	    Principal principal = request.getUserPrincipal();

	 	if(principal != null) {
            userService.findByUsername(principal.getName()).ifPresent(u -> currentUser = u);
            model.addAttribute("currentuser", currentUser);
	 	}
	}

	@GetMapping("/gestionarCreacion/{email}")
	public String validation(Model model, @PathVariable String email){
		model.addAttribute("email", email);

		return "ADMIN_validationView";
	}
    
    @GetMapping("/admin/editarUsuarios")
    public String listaUsuarios(Model model){
        List<User> userlist = userService.findAll();
		userlist.remove(currentUser);
        model.addAttribute("userlist", userlist);
        return "users"; 
    }
	
    @GetMapping("/admin/editarUsuarios/{id}")
	public String obtainUser(Model model, @PathVariable long id) {
		try { 

			User user = userService.findById(id).orElseThrow();
			user.getRol();
			String[] roles={"BASE", "ASO", "ADMIN"};
			List<Map<String, Object>> rolesL = new ArrayList<>();
        	for(int i=0;i<roles.length;i++){
            	Map<String, Object> option = new HashMap<>();
            	option.put("value", roles[i].toString());
            	option.put("selected", roles[i].equals(user.getRol()));
            	option.put("content", roles[i]);
            	rolesL.add(option);
        	}
        	model.addAttribute("userRoles", rolesL);
			model.addAttribute("user", user);

			return "edituser";

		} catch (Exception e) {
			e.printStackTrace();
			return "404"; 
		}
	}

	@PostMapping("/admin/editarUsuarios/{id}")
	public String editProfile(Model model, User newUser,@PathVariable long id){ 
		try { User user = userService.findById(id).orElseThrow();

			user.setUsername(newUser.getUsername());
			user.setEmail(newUser.getEmail());
			user.setRol(newUser.getRol());
			userService.save(user);

			return "redirect:/admin/editarUsuarios";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error"; 
		}
	}

	@PostMapping("/admin/editarUsuarios/{id}/delete")
	public String deleteProfile(Model model, User newUser,@PathVariable long id){
		try { User user = userService.findById(id).orElseThrow();
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
			return "redirect:/admin/editarUsuarios";
		} catch (Exception e) {
			return "redirect:/error"; 
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
