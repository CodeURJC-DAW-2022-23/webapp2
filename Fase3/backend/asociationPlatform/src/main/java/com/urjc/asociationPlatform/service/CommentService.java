package com.urjc.asociationPlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urjc.asociationPlatform.model.Comment;
import com.urjc.asociationPlatform.repository.CommentRepository;

import antlr.debug.Event;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void save(Comment comment) {
		commentRepository.save(comment);
	}

	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	public Optional<Comment> findById(long id) {
		Optional<Comment> findById = commentRepository.findById(id);
		return findById;
	}

    public void deleteById(long id){
		commentRepository.deleteById(id);
	}
}
