package com.urjc.asociationPlatform.controller.restController;

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

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/aso")
public class AssoRestController {
  
  @Autowired
  private AsociationService assoService;

  @Autowired
  private UserService userService;

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

  @GetMapping("/miAsociacion")
  public ResponseEntity<Asociation> getMyAsso(HttpServletRequest request) {
    User currentUser = checkAdminOrAsso("ASO", request);
    
    if (currentUser == null)
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    Optional<Asociation> asso = assoService.findByOwner(currentUser);
    if (asso.isEmpty()) {
      System.out.println("2");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Asociation> getById(@PathVariable long id) {
    Optional<Asociation> asso = assoService.findById(id);
    if (asso.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/asociationsList")
  public ResponseEntity<List<Asociation>> ListAsso() {
    return ResponseEntity.ok(assoService.findAll());
  }

  @PutMapping("/miAsociacion")
  public ResponseEntity<Asociation> editMyAsso(@RequestBody Asociation asso, HttpServletRequest request) {
    return edditAssoAux("ASO", request, asso);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Asociation> editAsso(@RequestBody Asociation asso, HttpServletRequest request) {
    return edditAssoAux("ADMIN", request, asso);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Asociation> deleteAsso(@PathVariable long id, HttpServletRequest request) {
    if (checkAdminOrAsso("ADMIN", request) == null)
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    Optional<Asociation> asso = assoService.findById(id);
    if (asso.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    assoService.deleteById(id);
    return ResponseEntity.ok(asso.get());
  }

  private ResponseEntity<Asociation> edditAssoAux(String type, HttpServletRequest request, @RequestBody Asociation asso) {
    User currentUser = checkAdminOrAsso(type, request);
    
    if (currentUser == null)
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    Optional<Asociation> oldAsso = assoService.findById(currentUser.getId());

    if (oldAsso.isEmpty())
      return ResponseEntity.notFound().build();

    asso.setId(currentUser.getId());
    assoService.save(asso);
    return ResponseEntity.ok(asso);
  }

  private User checkAdminOrAsso(String type, HttpServletRequest request) {
    User currentUser = getUser(request);
    if (currentUser == null || !currentUser.getRol().equals(type))
      return null;

    return currentUser;
  }
}
