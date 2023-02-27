package com.urjc.asociationPlatform.model;

import javax.persistence.*;

@Entity
public class Coment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id")
    private Long id;

    private String coment_user;
    private String description;
    private String time;
    
    public Coment(String coment_user, String description, String time){
    this.coment_user = coment_user;
    this.description = description;
    this.time = time;
    }

    public String getComentUser() {
        return coment_user;
    }
    public void setComentUser(String coment_user) {
        this.coment_user = coment_user;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    
  
}

