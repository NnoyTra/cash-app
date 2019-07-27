package com.nnoytra.request;

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
public class AddressRequest {
	@NotEmpty @NotNull
	private String state, city, street;
	@NotEmpty @NotNull @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Zip code most be only numbers")
	@Size(max = 5, min = 5, message = "Zip code most have 5 digits")
	private String zipCode;
}
