package com.nnoytra.converter.utils;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nnoytra.entities.Address;
import com.nnoytra.entities.User;
import com.nnoytra.entities.UserContact;
import com.nnoytra.repository.RoleRepository;
import com.nnoytra.request.AddressRequest;
import com.nnoytra.request.UserContactRequest;
import com.nnoytra.request.UserRequest;
import com.nnoytra.rest.AddressRest;
import com.nnoytra.rest.RoleRest;
import com.nnoytra.rest.UserContactRest;
import com.nnoytra.rest.UserRest;

@Component
public class UserUtils {
	
	@Autowired private  PasswordEncoder passwordEncoder;
	@Autowired private  ModelMapper modelMapper;
	@Autowired private RoleRepository roleRepository;	

	public  User convertUserRequestToUserEntity(UserRequest userRequest) {
		AddressRequest addressRequest = userRequest.getUserContactRequest().getAddressRequest();
		Address newAddress = modelMapper.map(addressRequest, Address.class);
		UserContactRequest userContactRequest = userRequest.getUserContactRequest();
		UserContact newUserContact = modelMapper.map(userContactRequest, UserContact.class);
		newUserContact.setAddress(newAddress);
		
		User newUser = User.builder()
									.username(userRequest.getUsername())
									.password(passwordEncoder.encode(userRequest.getPassword()))
									.isAccountNonExpired(true)
									.isAccountNonLocked(true)
									.isCredentialsNonExpired(true)
									.isEnabled(true)
									.userContact(newUserContact)
									.userID(UUID.randomUUID().toString())
									.role(roleRepository.findByRole("USER"))
									.build();
		return newUser;
	}
	
	public UserRest convertUserEntityToUserRest(User saveUser) {
		AddressRest addressRest = modelMapper.map(saveUser.getUserContact().getAddress(), AddressRest.class);
		RoleRest roleRest = modelMapper.map(saveUser.getRole(), RoleRest.class);
		UserContactRest userContactRest = modelMapper.map(saveUser.getUserContact(), UserContactRest.class);
		userContactRest.setAddressRest(addressRest);
		UserRest userRest = UserRest.builder()
											.username(saveUser.getUsername())
											.userContactRest(userContactRest)
											.roleRest(roleRest)
											.userID(saveUser.getUserID())
											.build();
		return userRest;
	}
}