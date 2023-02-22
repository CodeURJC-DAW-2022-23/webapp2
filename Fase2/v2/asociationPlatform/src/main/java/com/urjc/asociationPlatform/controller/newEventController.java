package com.urjc.asociationPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.service.EventService;

@Controller
public class newEventController {

  @Autowired
    private EventService eventService;

  @GetMapping("/newEvent")
  public String newEvent() {
    return "createEvent";
  }

  @PostMapping("/newEvent")
  public String createEvent(@RequestParam("name") String name, @RequestParam("date") String date,
                            @RequestParam("location") String location, @RequestParam("description") String description) {
    long millis = System.currentTimeMillis();
    java.sql.Date date2 = new java.sql.Date(millis);
    Event event = new Event(name, date2, "agosto", description, location, "association", null);
                              
    eventService.saveEvent(event);
    return "redirect:/gestionarEvento/";
  }
}
