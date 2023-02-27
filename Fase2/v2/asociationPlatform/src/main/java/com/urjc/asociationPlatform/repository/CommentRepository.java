package com.urjc.asociationPlatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urjc.asociationPlatform.model.Comment;

import antlr.debug.Event;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    Optional<Comment> findByEvent(Event event);
}
