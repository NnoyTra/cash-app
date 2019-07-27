package com.nnoytra.rest.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			Map<String, List<String>> errorMap = new HashMap<>();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();

			for (FieldError fieldError : fieldErrors) {
				String field = fieldError.getField();
				String defaultMessage = fieldError.getDefaultMessage();
				boolean containsField = errorMap.keySet().contains(field);

				if (containsField) {
					errorMap.get(field).add(defaultMessage);
				} else {
					errorMap.put(field, new ArrayList<String>(Arrays.asList(defaultMessage)));
				}
			}
			return new ResponseEntity<Object>(errorMap, HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<UserRest>(userService.saveUser(userRequest), HttpStatus.CREATED);
		}
	}

}
