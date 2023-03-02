package com.urjc.asociationPlatform.auxiliar;

import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.model.Event;


public class EventView {
    public boolean like;
    public boolean dislike;
    public int totalLikes;
    public int totalDislikes;
    public Event event;
    public EventView(Event event, boolean like, boolean dislike){
        this.event = event;
        this.like = like;
        this.dislike = dislike;
        this.totalLikes = event.getTotalLikes();
        this.totalDislikes = event.getTotalDislikes();
    }
}
