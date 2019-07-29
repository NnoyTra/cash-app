package com.nnoytra.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
		
//		ExecutorService exService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//		Future<UserRest> userRestFuture = exService.submit(()->{
//			User newUser = userUtils.convertUserRequestToUserEntity(userRequest);
//			User saveUser = userRepository.save(newUser);	
//			 return userUtils.convertUserEntityToUserRest(saveUser);
//		});
//		
//		
//		
//		try {
//			UserRest userRest = userRestFuture.get();
//			return userRest;
//		} catch (InterruptedException | ExecutionException e) {
//			
//			throw new RuntimeException("Problem with the Executor Service Call");
//		}finally {
//			exService.shutdown();
//		}
		User newUser = userUtils.convertUserRequestToUserEntity(userRequest);
		User saveUser = userRepository.save(newUser);	
		return userUtils.convertUserEntityToUserRest(saveUser);
		
	}

	

	

}
