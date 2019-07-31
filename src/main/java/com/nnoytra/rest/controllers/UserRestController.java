package com.nnoytra.rest.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nnoytra.request.UserRequest;
import com.nnoytra.rest.UserRest;
import com.nnoytra.services.UserService;

@RestController
@RequestMapping(UserRestController.API_USERS_BASE_URL)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserRestController {

	public static final String API_USERS_BASE_URL = "/api/users";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired @Qualifier("userServiceImpl")
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {

		if(userRequest == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (bindingResult.hasErrors()) {
			Map<String, List<String>> errorMap = new HashMap<>();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
	
			fieldErrors.stream().forEach(populateErrorMap(errorMap));

			return new ResponseEntity<Object>(errorMap, HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<UserRest>(userService.saveUser(userRequest), HttpStatus.CREATED);
		}
	}

	private Consumer<FieldError> populateErrorMap(Map<String, List<String>> errorMap) {
		return field -> {
			if (errorMap.keySet().contains(field.getField())) {
				errorMap.get(field.getField()).add(field.getDefaultMessage());
			} else
				errorMap.put(field.getField(), new ArrayList<>(Arrays.asList(field.getDefaultMessage())));
		};
	}

	@GetMapping
	public ResponseEntity<?> findAllPageable(@RequestParam (name = "init", required = false, defaultValue = "0") int init
										   , @RequestParam (name = "end", required = false, defaultValue = "5") int end){
		
		List<UserRest> usersRestList = userService.findAllPageable(init, end);
		
		return new ResponseEntity<List<UserRest>>(usersRestList, HttpStatus.OK);
	}

	@GetMapping("/principal")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getPrincipal(){
		
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return new ResponseEntity<String>(principal, HttpStatus.OK);
	}
	
	
	
}
