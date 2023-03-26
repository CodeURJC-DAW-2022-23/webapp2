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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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


  @Operation(summary = "Get personal Association")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Association obtained sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Asociation.class))}),
        @ApiResponse(responseCode = "404", description = "comment not found", content = @Content)
            
    })
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

  @Operation(summary = "Get Association by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Association obtained sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Asociation.class))}),
        @ApiResponse(responseCode = "404", description = "Association not found", content = @Content)
            
    })
  @GetMapping("/{id}")
  public ResponseEntity<Asociation> getById(@PathVariable long id) {
    Optional<Asociation> asso = assoService.findById(id);
    if (asso.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(asso.get(), HttpStatus.OK);
  }

  @Operation(summary = "Get Association list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Association list obtained sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Asociation.class))})
            
    })
  @GetMapping("/asociationsList")
  public ResponseEntity<List<Asociation>> ListAsso() {
    return new ResponseEntity<>(assoService.findAll(), HttpStatus.OK);
  }

  @Operation(summary = "Edit personal association")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Association edited sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Asociation.class))}),
        @ApiResponse(responseCode = "401", description = "current user doesn´t have the required permissions", content = @Content),
        @ApiResponse(responseCode = "403", description = "these changes can´t be made", content = @Content),
        @ApiResponse(responseCode = "404", description = "Asociation not found", content = @Content)
            
    })
  @PutMapping("/miAsociacion")
  public ResponseEntity<Asociation> editMyAsso(@RequestBody Asociation asso, HttpServletRequest request) {
    return edditAssoAux("ASO", request, asso, null);
  }

  @Operation(summary = "Edit association")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Association edited sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Asociation.class))}),
        @ApiResponse(responseCode = "401", description = "current user doesn´t have the required permissions", content = @Content),
        @ApiResponse(responseCode = "403", description = "these changes can´t be made", content = @Content),
        @ApiResponse(responseCode = "404", description = "Asociation not found", content = @Content)
            
    })
  @PutMapping("/{id}")
  public ResponseEntity<Asociation> editAsso(@RequestBody Asociation asso,@PathVariable long id, HttpServletRequest request) {
    return edditAssoAux("ADMIN", request, asso, id);
  }
  
  @Operation(summary = "Delete Association")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Association deleted sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Asociation.class))}),
        @ApiResponse(responseCode = "401", description = "current user doesn´t have the required permissions", content = @Content),
        @ApiResponse(responseCode = "404", description = "Association not found", content = @Content)
            
    })
  @DeleteMapping("/{id}")
  public ResponseEntity<Asociation> deleteAsso(@PathVariable long id, HttpServletRequest request) {
    if (checkAdminOrAsso("ADMIN", request) == null)
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    Optional<Asociation> asso = assoService.findById(id);
    if (asso.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    
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

    if (oldAsso.get().getId() != asso.getId())
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
}