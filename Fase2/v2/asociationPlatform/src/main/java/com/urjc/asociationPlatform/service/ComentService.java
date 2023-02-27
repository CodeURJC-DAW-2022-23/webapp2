package com.urjc.asociationPlatform.service;

public class ComentService {

    @Autowired
    private ComentRepository comentRepository;

    public void save(Event event) {
		eventRepository.save(event);
	}

	public Optional<Event> findByName(String name) {
		return eventRepository.findByName(name);
	}

	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	public Optional<Event> findById(long id) {
		Optional<Event> findById = eventRepository.findById(id);
		return findById;
	}

    public void deleteById(long id){
		eventRepository.deleteById(id);
	}
}
