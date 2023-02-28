package com.urjc.asociationPlatform.controller;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.urjc.asociationPlatform.auxiliar.CommentView;
import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.service.CommentService;
import com.urjc.asociationPlatform.service.EventService;
import com.urjc.asociationPlatform.service.UserService;


@Controller
public class EventDetailsController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    User currentUser;

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

	    Principal principal = request.getUserPrincipal();

	 	if(principal != null) {
            userService.findByUsername(principal.getName()).ifPresent(u -> currentUser = u);
            model.addAttribute("user", currentUser);
	 	}
	}

    @GetMapping("/infoEvento/{id}")
    public String infoEvento(Model model, @PathVariable long id){

        Event event = eventService.findById(id).orElseThrow();

        model.addAttribute("event", event);

        List<CommentView> commentsView = generateCommentViews(currentUser,event.getComments());
        model.addAttribute("commentsList", commentsView);

        return "detalles";
    }

    @PostMapping("/infoEvento/{id}/{id2}/like")
    public String likeButton(Model model, @PathVariable long id, @PathVariable long id2){
        try{
            Comment comment = commentService.findById(id2).orElseThrow();
            if(currentUser != null){
                System.out.print("\nHOLA\n");
                if(comment.isUserInFavorites(currentUser)){
                    System.out.print("\nMe quito\n");
                    comment.removeFavorites(currentUser);
                }else{
                    System.out.print("\nMe inserto\n");
                    comment.addFavorites(currentUser);
                }
                    
                commentService.save(comment);
            }
            return "redirect:/infoEvento/"+id;
        }
        catch (Exception e) {}
        return "redirect:/infoEvento/"+id;
    }

    private List<CommentView> generateCommentViews(User user, List<Comment> comments) {
        List<CommentView> list = new ArrayList<CommentView>();
        for(int i = 0; i < comments.size(); i++){
            if(user == null){
                list.add(new CommentView(comments.get(i), false));
            }
            else
                list.add(new CommentView(comments.get(i), comments.get(i).isUserInFavorites(user)));
        }
        return list;
    }

    
}
