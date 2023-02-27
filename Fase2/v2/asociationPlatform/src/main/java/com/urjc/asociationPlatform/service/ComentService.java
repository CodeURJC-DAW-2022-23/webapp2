package com.urjc.asociationPlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.urjc.asociationPlatform.model.Coment;
import com.urjc.asociationPlatform.repository.ComentRepository;

import antlr.debug.Event;

public class ComentService {

    @Autowired
    private ComentRepository comentRepository;

    public void save(Coment coment) {
		comentRepository.save(coment);
	}

	public Optional<Coment> findByEvent(Event event) {
		return comentRepository.findByEvent(event);
	}

	public List<Coment> findAll() {
		return comentRepository.findAll();
	}

	public Optional<Coment> findById(long id) {
		Optional<Coment> findById = comentRepository.findById(id);
		return findById;
	}

    public void deleteById(long id){
		comentRepository.deleteById(id);
	}
}
