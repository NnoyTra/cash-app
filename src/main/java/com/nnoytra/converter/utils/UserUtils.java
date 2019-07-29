package com.nnoytra.converter.utils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nnoytra.entities.Address;
import com.nnoytra.entities.Role;
import com.nnoytra.entities.User;
import com.nnoytra.entities.UserContact;
import com.nnoytra.entities.UserRole;
import com.nnoytra.repository.RoleRepository;
import com.nnoytra.repository.UserRoleRepository;
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
	@Autowired private UserRoleRepository userRoleRepository;

	
	public  User convertUserRequestToUserEntity(UserRequest userRequest) {
		AddressRequest addressRequest = userRequest.getUserContactRequest().getAddressRequest();
		Address newAddress = modelMapper.map(addressRequest, Address.class);
		UserContactRequest userContactRequest = userRequest.getUserContactRequest();
		UserContact newUserContact = modelMapper.map(userContactRequest, UserContact.class);
		newUserContact.setAddress(newAddress);
		
		UserRole userRole = new UserRole();
		userRole.setRole(roleRepository.findByRole("ROLE_ADMIN"));
		userRoleRepository.save(userRole);
		User newUser = User.builder()
									.username(userRequest.getUsername())
									.password(passwordEncoder.encode(userRequest.getPassword()))
									.isAccountNonExpired(true)
									.isAccountNonLocked(true)
									.isCredentialsNonExpired(true)
									.isEnabled(true)
									.userContact(newUserContact)
									.userID(UUID.randomUUID().toString())
									.userRoles(Arrays.asList(userRole))
									.build();
		userRole.setUser(newUser);
		return newUser;
	}
	
	public UserRest convertUserEntityToUserRest(User saveUser) {
		
		List<Role> roles = saveUser.getUserRoles().stream()
															.map((userRole)->{
																	return userRole.getRole();
															})
															.collect(Collectors.toList());

		List<RoleRest> roleRestList = roles.stream().map((role)->{
																	return modelMapper.map(role, RoleRest.class);
																	})
													.collect(Collectors.toList());
		
		AddressRest addressRest = modelMapper.map(saveUser.getUserContact().getAddress(), AddressRest.class);
		UserContactRest userContactRest = modelMapper.map(saveUser.getUserContact(), UserContactRest.class);
		userContactRest.setAddressRest(addressRest);
		UserRest userRest = UserRest.builder()
											.username(saveUser.getUsername())
											.userContactRest(userContactRest)
											.roleRestList(roleRestList)
											.userID(saveUser.getUserID())
											.build();
		return userRest;
	}
}