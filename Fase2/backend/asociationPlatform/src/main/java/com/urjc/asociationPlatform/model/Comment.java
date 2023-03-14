package com.urjc.asociationPlatform.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Event event;
    private String comment_user;
    private String description;
    private String time;
    @ManyToMany(cascade=CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<User> favorites = new HashSet<>();
    
    public Comment (){}
    
    public Comment(String comment_user, String description, String time){
    this.comment_user = comment_user;
    this.description = description;
    this.time = time;
    }

    public boolean isUserInFavorites(User user){
        return favorites.contains(user);
    }

    public int getTotalFavorites(){
        return favorites.size();
    }

    public boolean addFavorites(User user){
        return favorites.add(user);
    }

    public boolean removeFavorites(User user){
        return favorites.remove(user);
    }

    public String getCommentUser() {
        return comment_user;
    }
    public void setCommentUser(String comment_user) {
        this.comment_user = comment_user;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
     
}

