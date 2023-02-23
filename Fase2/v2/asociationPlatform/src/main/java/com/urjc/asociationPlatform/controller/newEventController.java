package com.urjc.asociationPlatform.controller;

import java.sql.Date;
import java.util.ArrayList;

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

  private String[] month = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO",
                            "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};

  @GetMapping("/newEvent")
  public String newEvent() {
    return "createEvent";
  }

  @PostMapping("/newEvent")
  public String createEvent(@RequestParam("name") String name, @RequestParam("date") String date,
                            @RequestParam("location") String location, @RequestParam("description") String description) {
    Date date2 = Date.valueOf(date.substring(0, date.length() - 6));
    // String month1 = month[date.toString().substring(5, date.length() - 6)];
    Event event = new Event(name, date2, "agosto", description, location, "association", null);                  
    eventService.saveEvent(event);
    return "redirect:/gestionarEvento/";
  }
}
