package com.nnoytra.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnoytra.converter.utils.UserUtils;
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
	@Autowired private UserUtils userUtils;

	@Override
	public List<UserRest> findAllPageable(int init, int end) {
		
		Pageable pageable = PageRequest.of(init, end);
		List<User> users = userRepository.findAll(pageable).getContent();
		
		if(users.isEmpty()) { return null;}
		else {
			
			List<UserRest> usersRest = new ArrayList<>();
			for(User user : users) {
				usersRest.add(userUtils.convertUserEntityToUserRest(user));
			}
			
			return usersRest ;
		}
		
		
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
	@Transactional
	public UserRest saveUser(UserRequest userRequest) {
		
		User newUser = userUtils.convertUserRequestToUserEntity(userRequest);
		User saveUser = userRepository.save(newUser);	
		return userUtils.convertUserEntityToUserRest(saveUser);
		
	}

	

	

}
