package com.nnoytra.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRest {

	private String userID, username;
	private UserContactRest userContactRest;
	private RoleRest roleRest;
}
