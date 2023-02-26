package com.urjc.asociationPlatform.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id")
    private Long id;

    private String email;
    private String username;
    private String encodedPassword;
    private String rol;
    @ManyToOne
    private Asociation asociation;

    @OneToMany
        private List<Event> favoritos = new ArrayList<>();
    
    public User(){}

    //base user
    public User(String email, String username, String encodedPassword){
        this.username = username;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.rol = "base";
    }

    //admin user
     public User(String username, String encodedPassword){
        this.username = username;
        this.encodedPassword = encodedPassword;
        this.rol = "admin";
     }

    //aso user
    public User(String username, String email, String encodedPassword,String asoname){
        this.username = username;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.rol = "aso";
    }

    public void setId(long id){
        this.id=id;
    }

    public long getId(){
        return this.id;
    }

    public List<Event> getFavoritos(){
        return this.favoritos;
    }

    public void setFavoritos(List<Event> favoritos){
        this.favoritos = favoritos;
    }

    public void addFavoritos(Event event){
        this.favoritos.add(event);
    }

    public void removeFavoritos(Event event){
        this.favoritos.remove(event);
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public String getencodedPassword(){
        return this.encodedPassword;
    }

    public String getRol(){
        return this.rol;
    }

    public Asociation getAsociation(){
        return this.asociation;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setencodedPassword(String encodedPassword){
        this.encodedPassword = encodedPassword;
    }

    public void setRol(String rol){
        this.rol =  rol;
    }

    public void setAsoname(Asociation asociation){
        this.asociation = asociation;
    }

}
