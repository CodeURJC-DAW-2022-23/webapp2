package com.urjc.asociationPlatform.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;

public class ComentaryController {

    //añadir y eliminar

    public String actualDateTime(){
        LocalDateTime fecha = LocalDateTime.now();
        
        return fecha.toString();
    }
}
