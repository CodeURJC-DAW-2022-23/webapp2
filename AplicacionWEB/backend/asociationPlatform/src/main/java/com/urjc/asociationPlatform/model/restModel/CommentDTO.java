package com.urjc.asociationPlatform.model.restModel;

import com.urjc.asociationPlatform.model.Comment;

public class CommentDTO {
    public Long id;
    public EventDTO event;
    public String comment_user;
    public String description;
    public String time;

    public CommentDTO(Comment comment){
        this.id = comment.getId();
        this.event = new EventDTO(comment.getEvent());
        this.comment_user = comment.getCommentUser();
        this.description = comment.getDescription();
        this.time = comment.getTime();
    }
}
