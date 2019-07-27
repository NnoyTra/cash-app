package com.nnoytra.request;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserContactRequest {
	
	@NotEmpty @NotNull @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Phone most be only numbers")
	@Size(min = 10, max = 10, message = "Phone most have 10 digits")
	private String phone;
	@NotEmpty @NotNull @Email
	private String email;
	@Valid
	private AddressRequest addressRequest;
}
