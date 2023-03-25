package com.urjc.asociationPlatform.controller.restController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
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
import org.springframework.web.util.UriBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;


@RestController
@RequestMapping("/api/events")
public class EventRestController {
    
    @Autowired
    private EventService eventService;

    @Autowired 
    private UserService userService;

    @Autowired
    private AsociationService asociationService;

    
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
    
    @DeleteMapping("/{id}")//tested
    public ResponseEntity<Event> deleteEvent(@PathVariable long id, HttpServletRequest request){
        Optional<Event> eventOp = eventService.findById(id);
        if(eventOp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Event event = eventOp.get();
        Principal principal = request.getUserPrincipal();
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
            return new ResponseEntity<>(HttpStatus.OK);
        }else if(user.getRol().equals("ADMIN")){
            eventService.deleteById(event.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @PostMapping("/new")//tested
    public ResponseEntity<Event> createEvent(MultipartFile newImage, Event event, HttpServletRequest request) throws SQLException, IOException, URISyntaxException{
        System.out.println(newImage==null);
        event.setImgUrl(getBlob(newImage));
        Principal principal = request.getUserPrincipal();
        if(principal==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<User> userOp = userService.findByUsername(principal.getName());
        if(userOp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userOp.get();
        if(user.getRol().equals("BASE")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else if(user.getRol().equals("ASO")){
            Optional<Asociation> asoOp = asociationService.findByOwner(user);
            if(asoOp.isPresent()){
                event.setAsociation(asoOp.get());
            }
            eventService.save(event);
            URI location = new URI("https://127.0.0.1:8443/api/events/"+event.getId());
            return ResponseEntity.created(location).body(event);
        }else if(user.getRol().equals("ADMIN")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @PutMapping("/{id}")//testeado
    public ResponseEntity<Event> editEvent(@PathVariable long id, Event event, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Event eventDB=eventService.findById(id).orElseThrow();
        event.setAsociation(eventDB.getAsociation());
        if(principal==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<User> userOp = userService.findByUsername(principal.getName());
        if(userOp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userOp.get();
        if(user.getRol().equals("BASE")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else if(user.getRol().equals("ASO")){
            System.out.println("ASO");
            
            if(!eventDB.getAsociation().getOwner().equals(user)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            
            event.setId(id);
            eventService.save(event);
            return new ResponseEntity<>(event,HttpStatus.OK);
        }else if(user.getRol().equals("ADMIN")){
            event.setId(id);
            eventService.save(event);
            return new ResponseEntity<>(event,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        
        
    }
    @PutMapping("/image/{id}")//testado
    public ResponseEntity<Event> setImage(@PathVariable long id, MultipartFile newImage, HttpServletRequest request) throws SQLException, IOException, URISyntaxException{
        Optional<Event> eventOp = eventService.findById(id);
        if(eventOp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Event event = eventOp.get();
        Principal principal = request.getUserPrincipal();
        if(principal==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<User> userOp = userService.findByUsername(principal.getName());
        if(userOp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userOp.get();
        if(user.getRol().equals("BASE")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else if(user.getRol().equals("ASO")){
            if(!event.getAsociation().getOwner().equals(user)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            event.setImgUrl(getBlob(newImage));
            eventService.save(event);
            return new ResponseEntity<>(HttpStatus.OK);
        }else if(user.getRol().equals("ADMIN")){
            event.setImgUrl(getBlob(newImage));
            eventService.save(event);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable long id) throws SQLException, IOException{
        Optional<Event> eventOp = eventService.findById(id);
        if(eventOp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Event event = eventOp.get();
        Resource image=getFile(event.getImage());
        System.out.println("length: "+image.contentLength());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(image.contentLength()).body(image);
    }

    //=====================auxiliar functions=======================
    public Blob getBlob(MultipartFile file) throws SQLException, IOException {
        Blob myBlob;
        byte[] bytes = file.getBytes();
        myBlob = new SerialBlob(bytes);
        return myBlob;
      }
    public Resource getFile(Blob image) throws SQLException, JsonProcessingException{

        byte[] data;
        Resource resource = null;
        System.out.println("length1: "+image.length());
        data = image.getBytes(1, (int)image.length());
        resource = new ByteArrayResource(data);
        return resource;
    }
}