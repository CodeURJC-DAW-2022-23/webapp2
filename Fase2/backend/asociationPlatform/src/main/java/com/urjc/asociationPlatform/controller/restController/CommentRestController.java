package main.java.com.urjc.asociationPlatform.controller.restController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.service.CommentService;
import com.urjc.asociationPlatform.service.EventService;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
    @Autowired
    private CommentService commentService;

    @Autowired
	private EventService eventService;

    @GetMapping("/")
	public ResponseEntity<Algo> funcion(HttpServletRequest request) {
		Optional<User> currentUser = userService.findByMail(request.getUserPrincipal().getName());
		if (currentUser.isPresent()) {
			
			return new ResponseEntity<>(userProfile, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    /* click comentario */
    /* 
    @PostMapping("/crearComentario/{id}")
	public String addComment(Model model, Comment newComent, @PathVariable long id, HttpServletRequest request){

        
        Principal principal = request.getUserPrincipal();

        if(principal != null){
            String fecha = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")
                    .format(LocalDateTime.now());
            newComent.setTime(fecha.toString());

            newComent.setCommentUser(principal.getName());
            
            commentService.save(newComent);
            
            Event event = eventService.findById(id).orElseThrow();
            event.addComment(newComent);
            eventService.save(event);

            return "redirect:/infoEvento/{id}";
        } else {
            return "/login";
        } 
		
	}

    @PostMapping("/admin/editarComentarios/{id}/delete")
	public String deleteComment(Model model, Comment newComent,@PathVariable long id){
		commentService.deleteById(id);
		return "redirect:/";
	}*/
}
