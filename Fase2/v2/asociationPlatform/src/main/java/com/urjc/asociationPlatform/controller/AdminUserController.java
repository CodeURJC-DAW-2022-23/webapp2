
package com.urjc.asociationPlatform.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class AdminUserController {

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
			}
	}

  @GetMapping("/admin/adminAsoc")
  public String listAsociations(Model model){
		List<Asociation> list = asoService.findAll();
    model.addAttribute("asocList", list);
    return "asociations"; 

  }

	@GetMapping("/admin/editAsoc/{id}")
	public String obtainAsoc(Model model, @PathVariable long id, HttpServletRequest request) {

		try { Asociation asoc = asoService.findById(id).orElseThrow();
			model.addAttribute("asociation", asoc);
			String[] faculties={"Facultad de Artes y Humanidades", "Facultad de CC de la Educacion y del Deporte y Estudios Interdisciplinares", "Facultad de Ciencias de la Comunicacion", "Facultad de Ciencias de la Economia y de la Empresa", "Facultad de Ciencias de la Salud", "Facultad de Ciencias Juridicas y Politicas"};
			String[] campus={"Alcorcón", "Aranjuez", "Fuenlabrada", "Móstoles", "Madrid-Vicalvaro", "Madrid-Quintana"};
			List<Map<String, Object>> facultiesL = createSelection(faculties, asoc.getFaculty());
			List<Map<String, Object>> campusL = createSelection(campus, asoc.getCampus());
        	model.addAttribute("listFaculties", facultiesL);
			model.addAttribute("listCampus", campusL);
			return "editAsociations";
		} catch (Exception e) {
            return "404";
        }
	}

	@PostMapping("/admin/editAsoc/{id}/modify")
	public String editProfile2(Model model, Asociation newAsoc, @PathVariable long id){
		try { asoService.findById(id).orElseThrow();
			
			if(!(newAsoc.getCampus().trim().isEmpty() || newAsoc.getFaculty().trim().isEmpty() || newAsoc.getName().trim().isEmpty())){
				newAsoc.setOwner(asoService.findById(id).get().getOwner());
				newAsoc.setId(id);
				asoService.save(newAsoc);
			}
			return "redirect:/admin/adminAsoc";
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

	private List<Map<String, Object>> createSelection(String[] text, String value){
		List<Map<String, Object>> list = new ArrayList<>();
		for(int i=0;i<text.length;i++){
			Map<String, Object> option = new HashMap<>();
			option.put("value", text[i].toString());
			option.put("selected", text[i].equals(value));
			option.put("content", text[i]);
			list.add(option);
		}
		return list;
	}
}
