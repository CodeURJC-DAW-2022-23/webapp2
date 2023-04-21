package com.urjc.asociationPlatform.controller.restController;

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.model.restModel.AsociationDTO;
import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.CommentService;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/aso")
public class AssoRestController {
  
  @Autowired
  private AsociationService assoService;

  @Autowired
  private UserService userService;

  @Autowired
  private EventService eventService;

  @Autowired
  private CommentService commentService;

	private User getUser(HttpServletRequest request) {
	  Principal principal = request.getUserPrincipal();
    Optional<User> currUser;

	 	if(principal != null) {
      currUser = userService.findByUsername(principal.getName());
      if (currUser.isPresent())
        return currUser.get();
    }

    return null;
	}


  @Operation(summary = "Create personal Association")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Association created sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AsociationDTO.class))}),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
            
    })
  @PostMapping("/miAsociacion")
  public ResponseEntity<AsociationDTO> create(@RequestBody Asociation asso, HttpServletRequest request){
    User currentUser = checkAdminOrAsso("ASO", request);
    
    if (currentUser == null)
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    asso.setOwner(currentUser);

    assoService.save(asso);
    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/admin/{id}")
      .buildAndExpand(asso.getId())
      .toUri();
    return ResponseEntity.created(location).body(new AsociationDTO(asso));
  }

  @Operation(summary = "Get personal Association")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Association obtained sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AsociationDTO.class))}),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
            
    })
  @GetMapping("/miAsociacion")
  public ResponseEntity<AsociationDTO> getMyAsso(HttpServletRequest request) {
    User currentUser = checkAdminOrAsso("ASO", request);
    
    if (currentUser == null)
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    Optional<Asociation> asso = assoService.findByOwner(currentUser);
    if (asso.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(new AsociationDTO(asso.get()), HttpStatus.OK);
  }

  @Operation(summary = "Get Association by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Association obtained sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AsociationDTO.class))}),
        @ApiResponse(responseCode = "404", description = "Association not found", content = @Content)
            
    })
  @GetMapping("/{id}")
  public ResponseEntity<AsociationDTO> getById(@PathVariable long id) {
    Optional<Asociation> asso = assoService.findById(id);
    if (asso.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(new AsociationDTO(asso.get()), HttpStatus.OK);
  }

  @Operation(summary = "Get Association list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Association list obtained sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AsociationDTO.class))})
            
    })
  @GetMapping("/asociationsList")
  public ResponseEntity<List<AsociationDTO>> ListAsso() {
    List<AsociationDTO> list = new ArrayList<AsociationDTO>();
    for(Asociation aso : assoService.findAll()){
      list.add(new AsociationDTO(aso));
    }
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @Operation(summary = "Edit personal association")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Association edited sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AsociationDTO.class))}),
        @ApiResponse(responseCode = "401", description = "current user doesn´t have the required permissions", content = @Content),
        @ApiResponse(responseCode = "403", description = "these changes can´t be made", content = @Content),
        @ApiResponse(responseCode = "404", description = "Asociation not found", content = @Content)
            
    })
  @PutMapping("/miAsociacion")
  public ResponseEntity<AsociationDTO> editMyAsso(@RequestBody Asociation asso, HttpServletRequest request) {
    System.out.println("hola");
    User currentUser = checkAdminOrAsso("ASO", request);
    
    if (currentUser == null)
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    asso.setOwner(currentUser);
    return edditAssoAux("ASO", request, asso, null);
  }

  @Operation(summary = "Edit association")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Association edited sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AsociationDTO.class))}),
        @ApiResponse(responseCode = "401", description = "current user doesn´t have the required permissions", content = @Content),
        @ApiResponse(responseCode = "403", description = "these changes can´t be made", content = @Content),
        @ApiResponse(responseCode = "404", description = "Asociation not found", content = @Content)
            
    })
  @PutMapping("/{id}")
  public ResponseEntity<AsociationDTO> editAsso(@RequestBody Asociation asso, @PathVariable long id, HttpServletRequest request) {
    Optional<Asociation> aso = assoService.findById(id);
    if(aso.isEmpty()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    asso.setOwner(aso.get().getOwner());
    return edditAssoAux("ADMIN", request, asso, id);
  }
  
  @Operation(summary = "Delete Association")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Association deleted sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AsociationDTO.class))}),
        @ApiResponse(responseCode = "401", description = "current user doesn´t have the required permissions", content = @Content),
        @ApiResponse(responseCode = "404", description = "Association not found", content = @Content)
            
    })
  @DeleteMapping("/{id}")
  public ResponseEntity<AsociationDTO> deleteAsso(@PathVariable long id, HttpServletRequest request) {
    if (checkAdminOrAsso("ADMIN", request) == null)
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    Optional<Asociation> asso = assoService.findById(id);
    if(asso.isEmpty()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Asociation asociation = asso.get();
    if (asso.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      List<Event> asoEvents= eventService.findAllbyAsociation(asociation);
      for(Event asoEvent : asoEvents){
          System.out.print("\n borrando evento \n");
          asoEvent = clearEvent(asoEvent);
          eventService.deleteById(asoEvent.getId());
      }
    assoService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  private ResponseEntity<AsociationDTO> edditAssoAux(String type, HttpServletRequest request, @RequestBody Asociation asso, Long id) {
    User currentUser = checkAdminOrAsso(type, request);
    
    if (currentUser == null)
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    if (type.equals("ASO"))
      id = assoService.findByOwner(currentUser).orElseThrow().getId();

    Optional<Asociation> oldAsso = assoService.findById(id);

    if (oldAsso.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    if (oldAsso.get().getId() != asso.getId() || asso.getOwner().getId() != oldAsso.get().getOwner().getId())
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    asso.setId(id);
    assoService.save(asso);
    return new ResponseEntity<>(new AsociationDTO(asso), HttpStatus.OK);
  }

  private User checkAdminOrAsso(String type, HttpServletRequest request) {
    User currentUser = getUser(request);
    if (currentUser == null || !currentUser.getRol().equals(type))
      return null;

    return currentUser;
  }
  private Event clearEvent(Event event){
		List<User> users = userService.findAll();
		for(User user:users){
		  if(user.isInFavorites(event)){
			user.removeFavoritos(event);
			userService.save(user);
		  }
			
		}
		List<Comment> comments=event.getComments();
		for(Comment comment:comments){
		  comment.clear();
		  commentService.save(comment);
		  commentService.deleteById(comment.getId());
		}
		event.clear();
		eventService.save(event);
		return event;
	}
}