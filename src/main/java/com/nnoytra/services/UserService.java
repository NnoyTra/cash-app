package com.nnoytra.services;

import java.util.List;

import com.nnoytra.request.UserRequest;
import com.nnoytra.rest.UserRest;

public interface UserService {

	List<UserRest> findByLastname(String lastname);
	
	UserRest findByUsername(String username);
	UserRest findByUserID(String userID);
	
	UserRest saveUser(UserRequest userRequest);
	List<UserRest> findAllPageable(int init, int end);
	
}
