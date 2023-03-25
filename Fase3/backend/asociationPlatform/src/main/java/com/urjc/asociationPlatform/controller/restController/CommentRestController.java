package com.urjc.asociationPlatform.controller.restController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.service.CommentService;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private EventService eventService;
    
    
    @Operation(summary = "Get Comment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "comment obtained sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))}),
        @ApiResponse(responseCode = "400", description = "invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "comment is not created", content = @Content),
        @ApiResponse(responseCode = "403", description = "not enough privileges or admin is modifying itself", content = @Content),
        @ApiResponse(responseCode = "404", description = "comment not found", content = @Content)
            
    })

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable long id) {
        
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isPresent()) {
            return new ResponseEntity<>(comment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Post Comment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "comment posted sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))}),
        @ApiResponse(responseCode = "400", description = "invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "comment is not created", content = @Content),
        @ApiResponse(responseCode = "403", description = "not enough privileges or admin is modifying itself", content = @Content),
        @ApiResponse(responseCode = "404", description = "comment not found", content = @Content)
            
    })

    @PostMapping("/crearComentario/{id}")
	public ResponseEntity<Comment> postComment(Model model, @RequestParam Comment newComent, @PathVariable long id, HttpServletRequest request){

        Principal principal = request.getUserPrincipal();

        if(principal != null){
            try {
                String fecha = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a").format(LocalDateTime.now());
                newComent.setTime(fecha.toString());
                newComent.setCommentUser(principal.getName());
                commentService.save(newComent);
            
                Event event = eventService.findById(id).orElseThrow();
                event.addComment(newComent);
                eventService.save(event);

                return new ResponseEntity<>(HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } 
	}

    @Operation(summary = "Remove Comment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "comment removed sucessfully",content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))}),
        @ApiResponse(responseCode = "400", description = "invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "comment is not created", content = @Content),
        @ApiResponse(responseCode = "403", description = "not enough privileges or admin is modifying itself", content = @Content),
        @ApiResponse(responseCode = "404", description = "comment not found", content = @Content)
            
    })

    @PostMapping("/admin/editarComentarios/{id}/delete")
	public ResponseEntity<Comment> deleteComment(Model model,@RequestParam Comment newComent, @PathVariable long id){
        try {
            commentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
