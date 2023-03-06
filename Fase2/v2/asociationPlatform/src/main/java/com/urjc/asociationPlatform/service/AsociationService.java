package com.urjc.asociationPlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.repository.AsociationRepository;

@Service
public class AsociationService {

    @Autowired
	private AsociationRepository asociations;

	public void save(Asociation asociation) {
		asociations.save(asociation);
	}

	public Optional<Asociation> findByName(String name) {
		return asociations.findByName(name);
	}

	public Optional<Asociation> findByCampus(String campus) {
	 	return asociations.findByCampus(campus);
	}

	public Optional<Asociation> findByOwner(User owner) {
		return asociations.findByOwner(owner);
   }

	public List<Asociation> findAll() {
		return asociations.findAll();
	}

	public Optional<Asociation> findById(long id) {
		Optional<Asociation> findById = asociations.findById(id);
		return findById;
	}

	public boolean existName(String name) {
	 	Optional<Asociation> asociation = findByName(name);
	 	return asociation.isPresent();
	}

    public void deleteById(long id) {
		asociations.deleteById(id);
    }
}
