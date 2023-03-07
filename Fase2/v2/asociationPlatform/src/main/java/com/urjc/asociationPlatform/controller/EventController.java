
package com.urjc.asociationPlatform.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Blob;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;

import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;

import java.io.IOException;
import java.security.Principal;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;

	  @Autowired
    private UserService userService;

    @Autowired
    AsociationService asociationService;

    User currentUser;

		private String[] campusValue={"ALCORCON","ARANJUEZ","FUENLABRADA","MOSTOLES","MADRID-VICALVARO","MADRID-QUINTANA"};

    private String[] month = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO",
                            "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};

    Event e;

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

	    Principal principal = request.getUserPrincipal();

	 	if(principal != null) {
            userService.findByUsername(principal.getName()).ifPresent(u -> currentUser = u);
            model.addAttribute("user", currentUser);
	 	}
	}
 
    
    @GetMapping("/admin/editarEventos")
    public String listaEventos(Model model){
        List<Event> eventlist = eventService.findAll();
        model.addAttribute("eventlist", eventlist);
        return "events"; 
    }

	@GetMapping("/admin/editarEventos/{id}")
  public ModelAndView obtainEvent(Model model, @PathVariable long id) {
    e = eventService.findById(id).orElseThrow();
    
		model.addAttribute("asociation", e.getAsociation());
    model.addAttribute("name", e.getName());
    model.addAttribute("date", e.getDate());
    model.addAttribute("startTime", e.getStartTime());
    model.addAttribute("endTime", e.getEndTime());

    List<Map<String, Object>> campusL = new ArrayList<>();
    for(int i = 0; i < campusValue.length; i++){
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
    return new ModelAndView("editevent");
  }

  //   @GetMapping("/admin/editarEventos/{id}")
	// public String obtainEvent(Model model, @PathVariable long id) {
	// 	Event event = eventService.findById(id).orElseThrow();

	// 	model.addAttribute("event", event);

	// 	return "editevent";
	// }

  @PostMapping("/admin/editarEventos/{id}")
  public String editEvents(@RequestParam("asociation") String asociation, @RequestParam("name") String name, @RequestParam("date") String date, 
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
    Blob blob;
    MultipartFile newImage;

      if (credits.equals("no")) creditsBool = false;
      else creditsBool = true;
      if (reservation.equals("no")) reservationBool = false;
      else reservationBool = true;

      blob = e.getImage();
      if (!image.isEmpty()) {
        newImage = image.get();
        try {
          blob = getBlob(newImage);
        } catch (SQLException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      changeEvent(e, asociation, name, date2, month[monthN-1], description, location, campus, creditsBool, 
      reservationBool, duration, blob, startTime, endTime);
      //eventService.updateById(newEvent, newEvent.getId());
      eventService.save(e);
      return "redirect:/admin/editarEventos";
  }

	// @PostMapping("/admin/editarEventos/{id}")
	// public String editEvents(Model model, Event event, @PathVariable long id) { 
	// 	try { event = eventService.findById(id).orElseThrow();
  //     event.setName(event.getName());
  //     event.setCredits(event.getCredits());
  //     event.setBooking(event.getBooking());
  //     event.setCampus(event.getCampus());
  //     event.setDate(event.getDate());
  //     event.setImgUrl(event.getImage());
  //     event.setDuration(event.getDuration());
  //     event.setDescription(event.getDescription());
  //     event.setLocation(event.getLocation());
  //     event.setMonth(event.getMonth());
  //     event.setStartTime(event.getStartTime());
  //     event.setEndTime(event.getEndTime());

	// 		return "redirect:/admin/editarEventos";
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 		return "404"; 
	// 	}
	// }

  private void changeEvent(Event newEvent, String asociation, String name, Date date, String month, String description, 
    String location, String campus, boolean credits, boolean booking, String duration, 
    Blob imgUrl, String startTime, String endTime) {
      newEvent.setAsociation(asociationService.findByName(asociation).get());;
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

	@PostMapping("/admin/editarEventos/{id}/delete")
	public String deleteProfile(Model model, Event newEvent,@PathVariable long id){

		eventService.deleteById(id);

		return "redirect:/admin/editarEventos";
	}

  public Blob getBlob(MultipartFile file) throws SQLException, IOException {
    Blob myBlob;
    byte[] bytes = file.getBytes();
    myBlob = new SerialBlob(bytes);
    return myBlob;
  }
}
