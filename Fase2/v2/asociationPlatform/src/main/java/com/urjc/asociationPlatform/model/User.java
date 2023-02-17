package com.urjc.asociationPlatform.model;

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
    private String asoname;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    
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

    public String getAsoname(){
        return this.asoname;
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

    public void setAsoname(String asoname){
        this.asoname = asoname;
    }

    public List<String> getRoles(){
        return this.roles;
    }

    public void setRoles(String ... roles){
        this.roles = List.of(roles);
    }

}
