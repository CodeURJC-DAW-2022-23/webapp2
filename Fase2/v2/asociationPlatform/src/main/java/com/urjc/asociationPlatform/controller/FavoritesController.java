package com.urjc.asociationPlatform.controller;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;

@Controller
public class FavoritesController {
    @Autowired
	private UserService userService;
    User currentUser;

    @Autowired
	private EventService eventService;

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

	    Principal principal = request.getUserPrincipal();

	 	if(principal != null) {
            userService.findByUsername(principal.getName()).ifPresent(u -> currentUser = u);
            model.addAttribute("user", currentUser);
            model.addAttribute("favoritesList", currentUser.getFavoritos());
	 	}
	}

    @GetMapping("agregarFavoritos/{id}")
    public String agregarFavoritos(Model model, @PathVariable long id, HttpServletRequest request){

        Principal principal = request.getUserPrincipal();

        if(principal != null){
            User user = userService.findByUsername(principal.getName()).orElseThrow();
            Event event = eventService.findById(id).orElseThrow();
            
            if(!user.getFavoritos().contains(event)){
                user.addFavoritos(event);
                userService.save(user);
            }
            return "redirect:/infoEvento/{id}";
            
        } else {
            return "/login";
        } 
    }

    // @GetMapping("/{userId}/añadirFavoritos/{eventId}")
    // public String addFavorites(Model model, @PathVariable long userId, @PathVariable long eventId){
    //     try {
    //         Event event = eventService.findById(eventId).orElseThrow();
    //         User user = userService.findById(userId).orElseThrow();

    //         if(!user.equals(currentUser)){
    //             return "404";
    //         }

    //         if(!currentUser.getFavoritos().contains(event)){
    //             currentUser.addFavoritos(event);
    //         }

    //         return "redirect:/";
            
    //     } catch (Exception e) {
    //         return "404";
    //     }
    // }

    @GetMapping("/eliminarFavoritos/{id}")
    public String deleteFavorites(Model model, @PathVariable long id ,HttpServletRequest request ){
        Principal principal = request.getUserPrincipal();

        if(principal != null){
            User user = userService.findByUsername(principal.getName()).orElseThrow();
            Event event = eventService.findById(id).orElseThrow();
            
            user.removeFavoritos(event);
            userService.save(user);
               
        }
        return "redirect:/miCuenta/favorites";
    }

    // @GetMapping("/{userId}/eliminarFavoritos/{eventId}")
    // public String removeFavorites(Model model, @PathVariable long userId, @PathVariable long eventId){
    //     try {
    //         Event event = eventService.findById(eventId).orElseThrow();
    //         User user = userService.findById(userId).orElseThrow();

    //         if(!user.equals(currentUser)){
    //             return "404";
    //         }

    //         if(currentUser.getFavoritos().contains(event)){
    //             currentUser.removeFavoritos(event);
    //         }

    //         return "redirect:/";
            
    //     } catch (Exception e) {
    //         return "404";
    //     }
    // }

    @GetMapping("/miCuenta/favoritos")
    public String showFavorites(Model model){
        return "favorites";
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException{
        Event event = eventService.findById(id).orElseThrow();

        if(event.getImage()!= null){
            Resource file = new InputStreamResource(event.getImage().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(event.getImage().length()).body(file);
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
