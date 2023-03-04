package com.urjc.asociationPlatform.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;

@Controller
public class NewEventController {

  @Autowired
    private EventService eventService;

  @Autowired
    private UserService userService;

  User currentUser;

  private String[] month = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO",
                            "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};

  @GetMapping("/newEvent")
  public String newEvent() {
    return "createEvent";
  }

  @PostMapping("/newEvent")
  public String createEvent(@RequestParam("name") String name, @RequestParam("date") String date, 
                            @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
                            @RequestParam("location") String location, @RequestParam("description") 
                            String description, @RequestParam("image") MultipartFile image, 
                            @RequestParam("campus") String campus, @RequestParam("credits") String credits,
                            @RequestParam("reservation") String reservation, HttpServletRequest request) {
    Date date2 = Date.valueOf(date);
    String[] endTimes = endTime.split(":");
    String[] startTimes = startTime.split(":");
    int durationInt = (Integer.parseInt(endTimes[0])*60 + Integer.parseInt(endTimes[1]))
     - (Integer.parseInt(startTimes[0])*60 + Integer.parseInt(startTimes[1]));
    String duration = String.valueOf(durationInt / 60)  + "h " + String.valueOf(durationInt % 60) + "min";
    int monthN = Integer.parseInt(date.substring(6, date.length() - 3));
    boolean creditsBool;
    boolean reservationBool;
    String association = " ";

    Principal principal = request.getUserPrincipal();

	 	if(principal != null) {
	 		userService.findByUsername(principal.getName()).ifPresent(u -> currentUser = u);
      if(currentUser!=null){
        association = currentUser.getAsociation().getName();
      }
      
	 	} 

    if (credits.equals("no")) creditsBool = false;
    else creditsBool = true;
    if (reservation.equals("no")) reservationBool = false;
    else reservationBool = true;

    try {
      Event event = new Event(name, date2, month[monthN-1], description, location, association,
                              campus, creditsBool, reservationBool, duration, getBlob(image), startTime, endTime);                  
      eventService.saveEvent(event);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return "redirect:/miEspacio/";

  }

    public Blob getBlob(MultipartFile file) throws SQLException, IOException {
      Blob myBlob;
      byte[] bytes = file.getBytes();
      myBlob = new SerialBlob(bytes);
      return myBlob;
    }
}