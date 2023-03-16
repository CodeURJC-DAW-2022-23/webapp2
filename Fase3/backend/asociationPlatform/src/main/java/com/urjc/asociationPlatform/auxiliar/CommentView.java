package com.urjc.asociationPlatform.auxiliar;

import com.urjc.asociationPlatform.model.Comment;

public class CommentView {
    public boolean like;
    public int totalLikes;
    public Comment comment;
    public CommentView(Comment comment, boolean like){
        this.comment = comment;
        this.like = like;
        this.totalLikes = comment.getTotalFavorites();
    }
}
