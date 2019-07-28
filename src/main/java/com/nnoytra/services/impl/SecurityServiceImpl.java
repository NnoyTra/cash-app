package com.nnoytra.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnoytra.entities.User;
import com.nnoytra.repository.UserRepository;

@Service
public class SecurityServiceImpl implements UserDetailsService{

	@Autowired private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User findByUsername = userRepository.findByUsername(username);
		if(findByUsername != null)
			return findByUsername;
		else {
			StringBuilder message = new StringBuilder();
			message.append("Username ");
			message.append(username);
			message.append(" was not found in our database");
			
			throw new UsernameNotFoundException(message.toString());
		}
	}

}
