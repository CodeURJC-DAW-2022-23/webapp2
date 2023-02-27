package com.urjc.asociationPlatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urjc.asociationPlatform.model.Coment;

import antlr.debug.Event;

public interface ComentRepository extends JpaRepository<Coment, Long>{
    Optional<Coment> findByEvent(Event event);
}
