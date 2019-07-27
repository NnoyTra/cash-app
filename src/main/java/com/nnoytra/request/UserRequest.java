package com.nnoytra.request;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
	
	@Column(unique = true, nullable = false, updatable = true)
	@NotEmpty @NotNull @Size(min = 3, message = "Username most have more than 3 characteres")
	private String username;
	
	@Column(unique = true, nullable = false, updatable = true)
	@NotEmpty @NotNull @Size(min = 7, message = "Password most have more than 7 characteres")
	private String password;
	
	@Valid
	private UserContactRequest userContactRequest;	
}
