package com.urjc.asociationPlatform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    /*@Query("SELECT * FROM user WHERE asociation_id LIKE :name AND month = :month AND campus = :campus AND asociation = :asociation")
    List<User> findByAsociation_id(Long asociation_id);*/
}
