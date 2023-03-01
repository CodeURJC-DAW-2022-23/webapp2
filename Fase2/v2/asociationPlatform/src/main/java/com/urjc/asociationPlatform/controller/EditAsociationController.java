package com.urjc.asociationPlatform.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.AsociationService;
import com.urjc.asociationPlatform.service.UserService;

import java.security.Principal;

@Controller
public class EditAsociationController {

    @Autowired
    AsociationService asoService;

	@Autowired
	UserService userService;

	User currentUser;
	Boolean isAsociation = false;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

	    Principal principal = request.getUserPrincipal();
			if(principal != null){
				userService.findByUsername(principal.getName()).ifPresent(u -> currentUser = u);
           		model.addAttribute("user", currentUser);
				// System.out.print("\n"+currentUser.getRol()+"\n");
				if(Objects.equals(currentUser.getRol(), "ASO")){
					isAsociation = true;
				}
				else{
					isAsociation = false;
				}
				System.out.print("\n"+isAsociation+"\n");
			}
	}

    @GetMapping("/admin/adminAsoc")
    public String listAsociations(Model model){

        List<Asociation> list = asoService.findAll();
        model.addAttribute("asocList", list);
        return "asociations"; 

    }
    
    @GetMapping("editAsoc/{id}")
	public String obtainAsociation(Model model, @PathVariable long id, HttpServletRequest request) {

		try { Asociation asoc = asoService.findById(id).orElseThrow();
			model.addAttribute("asociation", asoc);
			return "editAsociations";
		} catch (Exception e) {
            return "404";
        }
	}

	@GetMapping("/admin/editAsoc/{id}")
	public String obtainAsoc(Model model, @PathVariable long id, HttpServletRequest request) {

		try { Asociation asoc = asoService.findById(id).orElseThrow();
			model.addAttribute("asociation", asoc);
			return "editAsociations";
		} catch (Exception e) {
            return "404";
        }
	}

	@PostMapping("/editAsoc/{id}")
	public String editProfile(Model model, Asociation newAsoc, @PathVariable long id){
		try { asoService.findById(id).orElseThrow();
			String result;
			if(isAsociation){
				result = "myAso";
				System.out.print("\nAsociation\n");
			}
			else{
				result = "home";
				System.out.print("\nOtro\n");
			}
			if(newAsoc.getCampus().trim().isEmpty() || newAsoc.getFaculty().trim().isEmpty() || newAsoc.getName().trim().isEmpty()){
				return result;
			}
			else{
				newAsoc.setId(id);
				asoService.save(newAsoc);
				return result;
			}
        } catch (Exception e) {
            return "redirect:/404";
        }
	}

	@PostMapping("/admin/editAsoc/{id}")
	public String editProfile2(Model model, Asociation newAsoc, @PathVariable long id){
		try { asoService.findById(id).orElseThrow();
			String result;
			if(isAsociation){
				result = "redirect:/miEspacio";
				System.out.print("\nAsociation\n");
			}
			else{
				result = "redirect:/admin/adminAsoc";
				System.out.print("\nOtro\n");
			}
			if(newAsoc.getCampus().trim().isEmpty() || newAsoc.getFaculty().trim().isEmpty() || newAsoc.getName().trim().isEmpty()){
				return result;
			}
			else{
				newAsoc.setId(id);
				asoService.save(newAsoc);
				return result;
			}
        } catch (Exception e) {
            return "redirect:/404";
        }
	}

	@PostMapping("/admin/adminAsoc/{id}/delete")
	public String editProfile(Model model, @PathVariable long id){
		try { asoService.findById(id).orElseThrow();
			asoService.deleteById(id);
			return "redirect:/admin/adminAsoc";
        } catch (Exception e) {
            return "redirect:/404";
        }
	}
}
