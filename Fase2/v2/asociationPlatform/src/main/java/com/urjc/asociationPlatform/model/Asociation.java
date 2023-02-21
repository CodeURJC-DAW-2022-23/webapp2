package com.urjc.asociationPlatform.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Asociation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id")
    private Long id;

    private String asociationName;
    
    public Asociation(){}
}
