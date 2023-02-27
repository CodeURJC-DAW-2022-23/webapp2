package com.urjc.asociationPlatform.model;


import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import java.util.List;

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
    private String campus;
    private boolean creditos;
    private boolean reserva;
    private String duracion;
    
    @Lob
    private Blob image;

    //@ElementCollection(fetch = FetchType.EAGER)
    //private List<EventReview> reviews;
    public Event(){}
    
    public Event(String name, Date date, String month, String description, String location, String asociation, String campus, boolean creditos, boolean reserva, String duracion, Blob imgUrl) {
        this.name = name;
        this.date = date;
        this.month = month;
        this.description = description;
        this.location = location;
        this.asociation=asociation;
        this.campus=campus;
        this.creditos=creditos;
        this.reserva=reserva;
        this.duracion=duracion;
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
    
    public String getDateString(){
        return date.toString();
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
    
    public String getCampus() {
        return campus;
    }
    public void setCampus(String campus) {
        this.campus = campus;
    }
    public boolean isCreditos() {
        return creditos;
    }
    public void setCreditos(boolean creditos) {
        this.creditos = creditos;
    }
    public String getCreditosString(){
        if(creditos){
            return "Si";
        }
        return "No";
    }
    public boolean isReserva() {
        return reserva;
    }
    public void setReserva(boolean reserva) {
        this.reserva = reserva;
    }
    public String getReservaString(){
        if(reserva){
            return "Si";
        }
        return "No";
    }
    public String getDuracion() {
        return duracion;
    }
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
    public Blob getImage() {
        return image;
    }
    public void setImgUrl(Blob image) {
        this.image = image;
    }
    
}