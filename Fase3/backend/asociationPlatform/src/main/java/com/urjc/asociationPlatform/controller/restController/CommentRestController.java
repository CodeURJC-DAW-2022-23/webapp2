package main.java.com.urjc.asociationPlatform.controller.restController;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
    @Autowired
    private CommentService commentService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable long id) {
        
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isPresent()) {
            return new ResponseEntity<>(comment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //añadir comentario
    //añadir likes
}
