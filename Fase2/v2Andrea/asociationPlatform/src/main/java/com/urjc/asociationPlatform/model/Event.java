package com.urjc.asociationPlatform.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.mysql.cj.jdbc.Blob;


@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id")
    private Long id;

    private String name;
    private Date date;
    private String month;
    private String description;
    private String location;
    private String asociation;
    
    @Lob
    private Blob image;

    //@ElementCollection(fetch = FetchType.EAGER)
    //private List<eventReview> reviews;
    public Event(String name, Date date, String month, String description, String location, String asociation, Blob imgUrl) {
        this.name = name;
        this.date = date;
        this.month = month;
        this.description = description;
        this.location = location;
        this.asociation=asociation;
        this.image = imgUrl;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getAsociation() {
        return asociation;
    }
    public void setAsociation(String asociation) {
        this.asociation = asociation;
    }
    public Blob getImage() {
        return image;
    }
    public void setImgUrl(Blob image) {
        this.image = image;
    }
    
    
}
