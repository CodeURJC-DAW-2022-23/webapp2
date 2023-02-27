package com.urjc.asociationPlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.repository.CommentRepository;

import antlr.debug.Event;

public class CommentService {

    @Autowired
    private CommentRepository comentRepository;

    public void save(Comment coment) {
		comentRepository.save(coment);
	}

	public Optional<Comment> findByEvent(Event event) {
		return comentRepository.findByEvent(event);
	}

	public List<Comment> findAll() {
		return comentRepository.findAll();
	}

	public Optional<Comment> findById(long id) {
		Optional<Comment> findById = comentRepository.findById(id);
		return findById;
	}

    public void deleteById(long id){
		comentRepository.deleteById(id);
	}
}
