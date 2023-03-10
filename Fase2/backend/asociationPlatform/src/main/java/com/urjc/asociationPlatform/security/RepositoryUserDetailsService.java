package com.urjc.asociationPlatform.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.urjc.asociationPlatform.model.User;
import com.urjc.asociationPlatform.repository.UserRepository;



@Service
public class RepositoryUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		if (!user.getValidated()) {
			throw new UsernameNotFoundException("User not activated");
		}     

		List<GrantedAuthority> roles = new ArrayList<>();
		// for (String role : user.getRoles()) {
		// 	roles.add(new SimpleGrantedAuthority("ROLE_" + role));
		// }
		roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRol()));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
				user.getencodedPassword(), roles);

	}
		
}
