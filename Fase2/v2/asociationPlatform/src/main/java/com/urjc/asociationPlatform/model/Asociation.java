package com.urjc.asociationPlatform.model;

import javax.persistence.*;

@Entity
public class Asociation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id")
    private Long id;

    private String name;
    private String description;
    private String faculty;
    private String campus;
    
    public Asociation(){}

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

    public long getId() {
        return this.id;
    }
}