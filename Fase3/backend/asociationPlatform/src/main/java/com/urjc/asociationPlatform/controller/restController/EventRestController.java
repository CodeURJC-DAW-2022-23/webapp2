package com.urjc.asociationPlatform.controller.restController;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;


@RestController
@RequestMapping("/api/events")
public class EventRestController {
    
    @Autowired
    private EventService eventService;

    @Autowired 
    private UserService userService;

    
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable long id){
        Optional<Event> ev = eventService.findById(id);
        if (ev.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ev.get(), HttpStatus.OK);
    }
    @GetMapping("/filters")
    public ResponseEntity<List<Event>> searchFilters(String name, String month, String campus, String asociation, int page){
        page=page*6;
        List<Event> events = eventService.getEventsByFilters(name,month,campus,asociation,page);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable long id){
        Optional<Event> eventOp = eventService.findById(id);
        if(eventOp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Event event = eventOp.get();


        eventService.deleteById(event.getId());
            return new ResponseEntity<>(event,HttpStatus.OK);


        /*
        Principal principal =request.getUserPrincipal();
        if(principal==null){
            return new ResponseEntity<Event>(HttpStatus.UNAUTHORIZED);
        }
        Optional<User> userOp = userService.findByUsername(principal.getName());
        if(userOp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userOp.get();
        if(user.getRol().equals("BASE")){
            return new ResponseEntity<Event>(HttpStatus.FORBIDDEN);
        }
        else if(user.getRol().equals("ASO")){
            if(!event.getAsociation().getOwner().equals(user)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            eventService.deleteById(event.getId());
            return new ResponseEntity<>(event,HttpStatus.OK);
        }else if(user.getRol().equals("ADMIN")){
            eventService.deleteById(event.getId());
            return new ResponseEntity<>(event,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);*/

    }

    @PostMapping("/")
    public ResponseEntity<Event> createEvent(Event event, MultipartFile image) throws SQLException, IOException{
        event.setImgUrl(getBlob(image));
        eventService.save(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Event> editEvent(@PathVariable long id, Event event, MultipartFile image) throws SQLException, IOException{
        if(eventService.findById(id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        event.setId(id);
        event.setImgUrl(getBlob(image));
        eventService.save(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //=====================auxiliar functions=======================
    public Blob getBlob(MultipartFile file) throws SQLException, IOException {
        Blob myBlob;
        byte[] bytes = file.getBytes();
        myBlob = new SerialBlob(bytes);
        return myBlob;
      }
}
