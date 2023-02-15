package com.urjc.asociationplatflorm.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urjc.asociationplatflorm.Model.User;
import com.urjc.asociationplatflorm.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
	private UserRepository users;

	public void save(User user) {
		users.save(user);
	}

	public Optional<User> findByEmail(String mail) {
		return users.findByEmail(mail);
	}

	public Optional<User> findByName(String mail) {
	 	return users.findByEmail(mail);
	}

	public List<User> findAll() {
		return users.findAll();
	}

	public Optional<User> findById(long id) {
		Optional<User> findById = users.findById(id);
		return findById;
	}

	public boolean existEmail(String email) {
	 	Optional<User> user = findByEmail(email);
	 	return user.isPresent();
	}
}
