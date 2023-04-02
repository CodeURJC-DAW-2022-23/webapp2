package com.urjc.asociationPlatform.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

import com.urjc.asociationPlatform.repository.UserRepository;
import com.urjc.asociationPlatform.service.EventService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Asociation {
    public interface Asso {}

    @JsonView(Asso.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id")
    private Long id;

    @JsonView(Asso.class)
    private String name;

    @JsonView(Asso.class)
    @Lob
    @Column( length = 100000 )
    private String description;

    @JsonView(Asso.class)
    private String faculty;

    @JsonView(Asso.class)
    private String campus;

    @OneToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private User owner;

    

    public Asociation(){}

    public Asociation(String name, String descripcion, String campus, String faculty){
        this.name = name;
        this.description = descripcion;
        this.campus = campus;
        this.faculty = faculty;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Asociation other = (Asociation) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
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
    public long getOwnerId(){
        return this.owner.getId();
    }
}
