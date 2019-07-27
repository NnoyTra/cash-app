package com.nnoytra.services.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnoytra.entities.User;
import com.nnoytra.repository.RoleRepository;
import com.nnoytra.repository.UserRepository;
import com.nnoytra.request.UserRequest;
import com.nnoytra.rest.UserRest;
import com.nnoytra.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired private UserRepository userRepository;
	@Autowired private RoleRepository roleRepository;
	@Autowired private ModelMapper modelMapper;
	@Autowired private PasswordEncoder passwordEncoder;

	@Override
	public List<UserRest> findAll() {
		return null;
	}

	@Override
	public List<UserRest> findByLastname(String lastname) {
		return null;
	}

	@Override
	public UserRest findByUsername(String username) {
		return null;
	}

	@Override
	public UserRest findByUserID(String userID) {
		return null;
	}

	@Override
	public UserRest saveUser(UserRequest userRequest) {
		
		User newUser = UserUtils.convertUserRequestToUserEntity(userRequest);
		newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		newUser.setRole(roleRepository.findByRole("USER"));
		newUser.setUserID(UUID.randomUUID().toString());
		User saveUser = userRepository.save(newUser);	
		UserRest userRest = UserUtils.convertUserEntityToUserRest(saveUser);
		
		return userRest;
	}

	

	

}