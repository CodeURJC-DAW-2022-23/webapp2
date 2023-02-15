package com.urjc.asociationplatflorm.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urjc.asociationplatflorm.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Optional<User> findByName(String name);
    // Optional<User> findByEmail(String email);
    // List<User> findByUserType(String userType);
}
