package com.urjc.asociationPlatform.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.service.AsociationService;

@Controller
public class AdminAsociationController {

    @Autowired
    AsociationService asoService;

    @GetMapping("/adminAsoc")
    public String listAsociations(Model model){

        List<Asociation> list = asoService.findAll();
        model.addAttribute("asocList", list);
        return "asociations"; 

    }
    
    @GetMapping("/editAsoc/{id}")
	public String obtainAsoc(Model model, @PathVariable long id) {
		/* 
		Optional<Asociation> asoc = asoService.findById(id);
		if(asoc.isPresent() == false)
			return "404";
		else{
			model.addAttribute("asociation", asoc);
			return "editAsociations";
		}*/
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
			if(newAsoc.getCampus() == null || newAsoc.getCampus() == null || newAsoc.getName() == null ){
				System.out.print("\nVacio\n");
				return "redirect:/adminAsoc";
			}
			else{
				newAsoc.setId(id);
				System.out.print("\n"+newAsoc.getName()+"\n");
				asoService.save(newAsoc);
				return "redirect:/adminAsoc";
			}
        } catch (Exception e) {
			System.out.print("\nDead\n");
            return "redirect:/404";
        }
	}

	@PostMapping("/adminAsoc/{id}/delete")
	public String editProfile(Model model, @PathVariable long id){
		try { asoService.findById(id).orElseThrow();
			/*if(newAsoc.getCampus() == null || newAsoc.getCampus() == null || newAsoc.getName() == null ){
				System.out.print("\nVacio\n");
				return "redirect:/adminAsoc";
			}
			else{
				newAsoc.setId(id);
				System.out.print("\n"+newAsoc.getName()+"\n");
				asoService.save(newAsoc);
				return "redirect:/adminAsoc";
			}*/
			return "redirect:/adminAsoc";
        } catch (Exception e) {
			System.out.print("\nDead\n");
            return "redirect:/404";
        }
	}

	/*@PutMapping("/adminAsoc/{id}")
	public String editProfile(Model model, @PathVariable long id, @RequestBody Asociation newAsoc) throws IOException, SQLException {
		try { Asociation asoc = asoService.findById(id).orElseThrow();
			if(newAsoc.getCampus() == null || newAsoc.getCampus() == null || newAsoc.getName() == null ){
				return "redirect:/adminAsoc";
			}
			else{
				asoc.setName(newAsoc.getName());
				asoc.setDescription(newAsoc.getDescription());
				asoc.setFaculty(newAsoc.getFaculty());
				asoc.setCampus(newAsoc.getCampus());
				asoService.save(asoc);
				return "redirect:/adminAsoc";
			}
        } catch (Exception e) {
            return "redirect:/404";
        }
	}*/
}
