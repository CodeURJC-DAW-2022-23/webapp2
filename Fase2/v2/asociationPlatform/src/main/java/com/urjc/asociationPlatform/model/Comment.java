package com.urjc.asociationPlatform.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id")
    private Long id;

    private String comment_user;
    private String description;
    private String time;
    
    public Comment (){}
    
    public Comment(String comment_user, String description, String time){
    this.comment_user = comment_user;
    this.description = description;
    this.time = time;
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
     
}

