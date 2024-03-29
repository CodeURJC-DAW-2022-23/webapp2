package com.urjc.asociationPlatform.controller.restController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/events")
public class EventRestController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private AsociationService asociationService;

    @Operation(summary = "Get Event by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event getted succesfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)

    })
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable long id) {
        Optional<Event> ev = eventService.findById(id);
        if (ev.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ev.get(), HttpStatus.OK);
    }

    @Operation(summary = "Get events using filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events getted succesfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(type = "array", implementation = Event.class))) }),

    })
    @GetMapping("/filters")
    public ResponseEntity<Page<Event>> searchFilters(String name, String month, String campus, String asociation,
            int page) {
        // page=page*6;
        Page<Event> events = eventService.getEventsByFilters(name, month, campus, asociation, PageRequest.of(page, 6));
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @Operation(summary = "Delete event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "event removed sucessfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "401", description = "no user register", content = @Content),
            @ApiResponse(responseCode = "403", description = "not enouth privileges", content = @Content),
            @ApiResponse(responseCode = "404", description = "event not found", content = @Content)

    })
    @DeleteMapping("/{id}") // tested
    public ResponseEntity<Event> deleteEvent(@PathVariable long id, HttpServletRequest request) {
        Optional<Event> eventOp = eventService.findById(id);
        if (eventOp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Event event = eventOp.get();
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return new ResponseEntity<Event>(HttpStatus.UNAUTHORIZED);
        }
        Optional<User> userOp = userService.findByUsername(principal.getName());
        if (userOp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userOp.get();
        if (user.getRol().equals("BASE")) {
            return new ResponseEntity<Event>(HttpStatus.FORBIDDEN);
        } else if (user.getRol().equals("ASO")) {
            if (!event.getAsociation().getOwner().equals(user)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            eventService.deleteById(event.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (user.getRol().equals("ADMIN")) {
            eventService.deleteById(event.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @Operation(summary = "Create Event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "event created sucessfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "401", description = "no user register", content = @Content),
            @ApiResponse(responseCode = "403", description = "not enouth privileges", content = @Content),

    })
    @PostMapping("/new") // tested
    public ResponseEntity<Event> createEvent(MultipartFile newImage, Event event, HttpServletRequest request)
            throws SQLException, IOException, URISyntaxException {
        System.out.println(newImage == null);
        event.setImgUrl(getBlob(newImage));
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<User> userOp = userService.findByUsername(principal.getName());
        if (userOp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userOp.get();
        if (user.getRol().equals("BASE")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else if (user.getRol().equals("ASO")) {
            Optional<Asociation> asoOp = asociationService.findByOwner(user);
            if (asoOp.isPresent()) {
                event.setAsociation(asoOp.get());
            }
            event.setDuration(calculateDuration(event.getStartTime(), event.getEndTime()));
            event.setMonth(calculateMonth(event.getDate().toString()));
            eventService.save(event);
            URI location = new URI("https://127.0.0.1:8443/api/events/" + event.getId());
            return ResponseEntity.created(location).body(event);
        } else if (user.getRol().equals("ADMIN")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Operation(summary = "Edit event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "event edited sucessfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "401", description = "no user register", content = @Content),
            @ApiResponse(responseCode = "403", description = "not enouth privileges", content = @Content),
            @ApiResponse(responseCode = "404", description = "event not found", content = @Content)

    })
    @PutMapping("/{id}") // testeado
    public ResponseEntity<Event> editEvent(@PathVariable long id, Event event, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Optional<Event> eventDBOp = eventService.findById(id);
        if (eventDBOp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Event eventDB = eventDBOp.get();
        event.setAsociation(eventDB.getAsociation());
        event.setDuration(calculateDuration(event.getStartTime(), event.getEndTime()));
        event.setMonth(calculateMonth(event.getDate().toString()));
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<User> userOp = userService.findByUsername(principal.getName());
        if (userOp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userOp.get();
        if (user.getRol().equals("BASE")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else if (user.getRol().equals("ASO")) {
            System.out.println("ASO");

            if (!eventDB.getAsociation().getOwner().equals(user)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            event.setId(id);
            eventService.save(event);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else if (user.getRol().equals("ADMIN")) {
            event.setId(id);
            eventService.save(event);
            return new ResponseEntity<>(event, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @Operation(summary = "Change event image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "event image changed sucessfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "401", description = "no user register", content = @Content),
            @ApiResponse(responseCode = "403", description = "not enouth privileges", content = @Content),
            @ApiResponse(responseCode = "404", description = "event not found", content = @Content)

    })
    @PutMapping("/image/{id}") // testado
    public ResponseEntity<Event> setImage(@PathVariable long id, MultipartFile newImage, HttpServletRequest request)
            throws SQLException, IOException, URISyntaxException {
        Optional<Event> eventOp = eventService.findById(id);
        if (eventOp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Event event = eventOp.get();
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<User> userOp = userService.findByUsername(principal.getName());
        if (userOp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userOp.get();
        if (user.getRol().equals("BASE")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else if (user.getRol().equals("ASO")) {
            if (!event.getAsociation().getOwner().equals(user)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            System.out.println("------------------size: "+newImage.getSize());
            System.out.println("------------------size: "+getBlob(newImage).length());
            event.setImgUrl(getBlob(newImage));
            eventService.save(event);
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (user.getRol().equals("ADMIN")) {
            event.setImgUrl(getBlob(newImage));
            eventService.save(event);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @Operation(summary = "Get event image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "event image getted sucessfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "404", description = "event not found", content = @Content)

    })
    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable long id) throws SQLException, IOException {
        Optional<Event> eventOp = eventService.findById(id);
        if (eventOp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Event event = eventOp.get();
        Resource image = getFile(event.getImage());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(image.contentLength())
                .body(image);
    }

    @Operation(summary = "Get event list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of events correct", content = @Content)

    })
    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }
    @Operation(summary = "Give like to event")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "like done succesfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class))}),
        @ApiResponse(responseCode = "401", description = "no user register", content = @Content),
        @ApiResponse(responseCode = "404", description = "event not found", content = @Content)
            
    })
    @PostMapping("/like/{id}")
    public ResponseEntity<Event> giveLike(@PathVariable long id, HttpServletRequest request){
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
        event.addLike(user);
        eventService.save(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Give dislike to event")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "dislike done succesfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class))}),
        @ApiResponse(responseCode = "401", description = "no user register", content = @Content),
        @ApiResponse(responseCode = "404", description = "event not found", content = @Content)
            
    })
    @PostMapping("/dislike/{id}")
    public ResponseEntity<Event> giveDislike(@PathVariable long id, HttpServletRequest request){
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
        event.addDislike(user);
        eventService.save(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // =====================auxiliar functions=======================
    public Blob getBlob(MultipartFile file) throws SQLException, IOException {
        Blob myBlob;
        byte[] bytes = file.getBytes();
        myBlob = new SerialBlob(bytes);
        return myBlob;
    }

    public Resource getFile(Blob image) throws SQLException, JsonProcessingException {

        byte[] data;
        Resource resource = null;
        data = image.getBytes(1, (int) image.length());
        resource = new ByteArrayResource(data);
        return resource;
    }
    private String calculateDuration(String start, String end){
        String[] startParts=start.split(":");
        String[] endParts=end.split(":");
        String response="";
        if(Integer.parseInt(endParts[1])<Integer.parseInt(startParts[1])){
            response=(Integer.parseInt(endParts[0])-Integer.parseInt(startParts[0])-1)+"h ";
            response+= (60 + (Integer.parseInt(endParts[1])-Integer.parseInt(startParts[1])))+"min";
        }else{
            response=(Integer.parseInt(endParts[0])-Integer.parseInt(startParts[0]))+"h ";
            response+= Math.abs(Integer.parseInt(endParts[1])-Integer.parseInt(startParts[1]))+"min";
        }
        
        return response;
    }
    private String[] monthsValue={"All", "ENERO", "FEBRERO", "MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
    private String calculateMonth(String date){
        String[] dateParts=date.split("-");
        String response=monthsValue[Integer.parseInt(dateParts[1])];
        
        
        return response;
    }
}