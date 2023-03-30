package com.urjc.asociationPlatform.model.restModel;

import java.sql.Date;

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.Event;

public class EventDTO {

    public Long id;
    public String name;
    public Date date;
    public String month;
    public String description;
    public String location;
    public String asociation;
    public String campus;
    public boolean credits;
    public boolean booking;
    public String startTime;
    public String endTime;
    public String duration;

    public EventDTO(Event event){
        this.id = event.getId();
        this.name = event.getName();
        this.date = event.getDate();
        this.month = event.getMonth();
        this.description = event.getDescription();
        this.location = event.getLocation();
        this.asociation = event.getAsociationName();
        this.campus = event.getCampus();
        this.credits = event.getCredits();
        this.credits = event.getBooking();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.duration = event.getDuration();
    }
}
