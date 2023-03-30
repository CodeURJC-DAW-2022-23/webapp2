package com.urjc.asociationPlatform.model.restModel;

import com.urjc.asociationPlatform.model.Comment;

public class CommentDTO {
    private Long id;
    private EventDTO event;
    private String comment_user;
    private String description;
    private String time;

    public CommentDTO(Comment comment){
        this.id = comment.getId();
        this.event = new EventDTO(comment.getEvent());
        this.comment_user = comment.getCommentUser();
        this.description = comment.getDescription();
        this.time = comment.getTime();
    }
}
