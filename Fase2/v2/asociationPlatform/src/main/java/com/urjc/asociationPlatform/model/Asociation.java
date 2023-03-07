package com.urjc.asociationPlatform.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.urjc.asociationPlatform.repository.UserRepository;
import com.urjc.asociationPlatform.service.EventService;

@Entity
public class Asociation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id")
    private Long id;

    private String name;
    @Lob
    @Column( length = 100000 )
    private String description;
    private String faculty;
    private String campus;
    @OneToOne(cascade = CascadeType.ALL)
    private User owner;

    

    public Asociation(){}

    public Asociation(String name, String descripcion, String campus, String faculty){
        this.name = name;
        this.description = descripcion;
        this.campus = campus;
        this.faculty = faculty;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setFaculty(String faculty){
        this.faculty = faculty;
    }

    public void setCampus(String campus){
        this.campus = campus;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public String getFaculty(){
        return this.faculty;
    }

    public String getCampus(){
        return this.campus;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    public User getOwner() {
        return this.owner;
    }
}
