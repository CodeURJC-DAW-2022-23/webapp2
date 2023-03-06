package com.urjc.asociationPlatform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.User;

public interface AsociationRepository extends JpaRepository<Asociation, Long> {
    Optional<Asociation> findByName(String name);
    Optional<Asociation> findByCampus(String campus);
    Optional<Asociation> findByOwner(User owner);
}
