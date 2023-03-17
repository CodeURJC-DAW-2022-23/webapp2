package com.urjc.asociationPlatform.controller.restController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.service.EventService;


@RestController
@RequestMapping("/api/events")
public class EventRestController {
    
    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable long id){
        Optional<Event> ev = eventService.findById(id);
        if (!ev.isEmpty()) {
            return new ResponseEntity<>(ev.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/seach")
    public ResponseEntity<List<Event>> seachFilters(String name, String month, String campus, String asociation, int page){
        page=page*6;
        List<Event> events = eventService.getEventsByFilters(name,month,campus,asociation,page);
        return new ResponseEntity<>(events, HttpStatus.OK);

    }

}
