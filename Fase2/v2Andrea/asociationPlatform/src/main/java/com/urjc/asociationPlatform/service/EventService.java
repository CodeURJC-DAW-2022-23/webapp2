/*package com.urjc.asociationPlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.repository.UserRepository;

@Service
public class EventService {

    @Autowired
	private EventRepository events;

	public void save(Evento event) {
		events.save(event);
	}

	public Optional<Evento> findByName(String name) {
		return events.findByName(name);
	}

	public List<Evento> findAll() {
		return events.findAll();
	}

	public Optional<Evento> findById(long id) {
		Optional<Evento> findById = events.findById(id);
		return findById;
	}
}
*/