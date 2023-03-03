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
import com.urjc.asociationPlatform.auxiliar.EventView;
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
        EventView view = generateEventView(currentUser, event);
        model.addAttribute("view", view);

        List<CommentView> commentsView = generateCommentViews(currentUser,event.getComments());
        model.addAttribute("commentsList", commentsView);

        return "detalles";
    }

    @GetMapping("/infoEvento/{id}/like")
    public String likeEvent(Model model, @PathVariable long id){
        try{
            Event event = eventService.findById(id).orElseThrow();
            if(currentUser != null){
                if(event.isUserInLikes(currentUser)){
                    event.removeLike(currentUser);
                }else{
                    event.removeDislike(currentUser);
                    event.addLike(currentUser);
                }
                eventService.save(event);
                return "redirect:/infoEvento/"+id;
            }
            else{
                return "redirect:/login";
            }
        }
        catch (Exception e) {}
        return "redirect:/infoEvento/"+id;
    }

    @GetMapping("/infoEvento/{id}/dislike")
    public String dislikeEvent(Model model, @PathVariable long id){
        try{
            Event event = eventService.findById(id).orElseThrow();
            if(currentUser != null){
                if(event.isUserInDislikes(currentUser)){
                    event.removeDislike(currentUser);
                }else{
                    event.removeLike(currentUser);
                    event.addDislike(currentUser);
                }
                eventService.save(event);
                return "redirect:/infoEvento/"+id;
            }
            else{
                return "redirect:/login";
            }
        }
        catch (Exception e) {}
        return "redirect:/infoEvento/"+id;
    }

    @GetMapping("/infoEvento/{id}/{id2}/like")
    public String likeButton(Model model, @PathVariable long id, @PathVariable long id2){
        try{
            Comment comment = commentService.findById(id2).orElseThrow();
            if(currentUser != null){
                if(comment.isUserInFavorites(currentUser)){
                    comment.removeFavorites(currentUser);
                }else{
                    comment.addFavorites(currentUser);
                }
                commentService.save(comment);
                return "redirect:/infoEvento/"+id;
            }
            else{
                return "redirect:/login";
            }
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

    private EventView generateEventView(User user, Event event) {
        if(user == null){
            return new EventView(event,false,false);
        }
        else
            return new EventView(event,event.isUserInLikes(user),event.isUserInDislikes(user));
        
    }

    
}
