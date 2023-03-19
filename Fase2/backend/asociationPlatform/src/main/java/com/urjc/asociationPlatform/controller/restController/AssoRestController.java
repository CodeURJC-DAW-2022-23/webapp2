package com.urjc.asociationPlatform.controller.restController;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.UserService;

@RestController
@RequestMapping("/api/aso")
public class AssoRestController {
  
  @Autowired
  private AsociationService asoService;

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
    User currentUser = getUser(request);
    if (currentUser == null){
      System.out.println("1");
      return ResponseEntity.notFound().build();
    }
      
    Optional<Asociation> aso = asoService.findByOwner(currentUser);
    if (aso.isEmpty()){
      System.out.println("2");
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(aso.get()); //🥹
  }
}
