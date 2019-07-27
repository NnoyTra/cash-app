package com.nnoytra.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nnoytra.request.UserRequest;
import com.nnoytra.rest.UserRest;
import com.nnoytra.services.UserService;

@RestController
@RequestMapping(UserRestController.API_USERS_BASE_URL)
public class UserRestController {

	public static final String API_USERS_BASE_URL = "/api/users";
	@Autowired private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserRest> saveUser(@RequestBody UserRequest userRequest){
		
		return new ResponseEntity<UserRest>(userService.saveUser(userRequest), HttpStatus.CREATED);
	}

}
