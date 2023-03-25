package com.urjc.asociationPlatform.controller.restController;

import java.net.URI;
import java.security.Principal;
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

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.CommentService;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;
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

  @PostMapping("/miAsociacion")
  public ResponseEntity<Asociation> create(@RequestBody Asociation asso, HttpServletRequest request){
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
    return ResponseEntity.created(location).body(asso);
  }

  @GetMapping("/miAsociacion")
  public ResponseEntity<Asociation> getMyAsso(HttpServletRequest request) {
    User currentUser = checkAdminOrAsso("ASO", request);
    
    if (currentUser == null)
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    Optional<Asociation> asso = assoService.findByOwner(currentUser);
    if (asso.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(asso.get(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Asociation> getById(@PathVariable long id) {
    Optional<Asociation> asso = assoService.findById(id);
    if (asso.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(asso.get(), HttpStatus.OK);
  }

  @GetMapping("/asociationsList")
  public ResponseEntity<List<Asociation>> ListAsso() {
    return new ResponseEntity<>(assoService.findAll(), HttpStatus.OK);
  }

  @PutMapping("/miAsociacion")
  public ResponseEntity<Asociation> editMyAsso(@RequestBody Asociation asso, HttpServletRequest request) {
    return edditAssoAux("ASO", request, asso, null);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Asociation> editAsso(@RequestBody Asociation asso,@PathVariable long id, HttpServletRequest request) {
    return edditAssoAux("ADMIN", request, asso, id);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Asociation> deleteAsso(@PathVariable long id, HttpServletRequest request) {
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

  private ResponseEntity<Asociation> edditAssoAux(String type, HttpServletRequest request, @RequestBody Asociation asso, Long id) {
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
    return new ResponseEntity<>(asso, HttpStatus.OK);
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