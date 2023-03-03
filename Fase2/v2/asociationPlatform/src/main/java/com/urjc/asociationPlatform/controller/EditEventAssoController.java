package com.urjc.asociationPlatform.controller;

import java.io.IOException;
import java.lang.StackWalker.Option;
import java.security.Principal;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialBlob;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;

@Controller
public class EditEventAssoController {

  @Autowired
    private EventService eventService;

  User currentUser;
  Optional<Event> event;

  private String[] month = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO",
                            "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
  private String[] campusValue={"ALCORCON","ARANJUEZ","FUENLABRADA","MOSTOLES","MADRID-VICALVARO","MADRID-QUINTANA"};

  @GetMapping("/editEvent/{id}")
  public ModelAndView editEvent(ModelMap model, @PathVariable long id) {
    this.event = eventService.findById(id);
    if (event.isEmpty())
      return new ModelAndView("gestionarEvento", model);

    Event e = event.get();
    model.addAttribute("name", e.getName());
    model.addAttribute("date", e.getDate());
    model.addAttribute("startTime", e.getStartTime());
    model.addAttribute("endTime", e.getEndTime());

    List<Map<String, Object>> campusL = new ArrayList<>();
    for(int i=0;i<campusValue.length;i++){
      Map<String, Object> option = new HashMap<>();
      option.put("value", campusValue[i].toString());
      option.put("selected", campusValue[i].equals(e.getCampus()));
      option.put("content", campusValue[i]);
      campusL.add(option);
    }
    model.addAttribute("campus", campusL);
    model.addAttribute("location", e.getLocation());
    model.addAttribute("credits", e.getCredits());
    model.addAttribute("description", e.getDescription());
    model.addAttribute("reservation", e.getBooking());
    return new ModelAndView("editEventAsso", model);
  }

  @PostMapping("/editEvent")
  public String editEvent(@RequestParam("name") String name, @RequestParam("date") String date, 
                            @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
                            @RequestParam("location") String location, @RequestParam("description") 
                            String description, @RequestParam Optional<MultipartFile> image, 
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
    Event newEvent;
    Blob blob;
    MultipartFile newImage;

    if (!this.event.isEmpty()){
      newEvent = this.event.get();
      if (credits.equals("no")) creditsBool = false;
      else creditsBool = true;
      if (reservation.equals("no")) reservationBool = false;
      else reservationBool = true;

      blob = newEvent.getImage();
      if (image.isEmpty()) {
        newImage = image.get();
        try {
          blob = getBlob(newImage);
        } catch (SQLException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      changeEvent(newEvent, name, date2, month[monthN-1], description, location, campus, creditsBool, 
      reservationBool, duration, blob, startTime, endTime);
  }
    return "miEspacio";
  }

    public Blob getBlob(MultipartFile file) throws SQLException, IOException {
      Blob myBlob;
      byte[] bytes = file.getBytes();
      myBlob = new SerialBlob(bytes);
      return myBlob;
    }

    public void changeEvent(Event newEvent, String name, Date date, String month, String description, 
    String location, String campus, boolean credits, boolean booking, String duration, 
    Blob imgUrl, String startTime, String endTime) {
      newEvent.setName(name);
      newEvent.setCredits(credits);
      newEvent.setBooking(booking);
      newEvent.setCampus(campus);
      newEvent.setDate(date);
      newEvent.setImgUrl(imgUrl);
      newEvent.setDuration(duration);
      newEvent.setDescription(description);
      newEvent.setLocation(location);
      newEvent.setMonth(month);
      newEvent.setStartTime(startTime);
      newEvent.setEndTime(endTime);
    }
}