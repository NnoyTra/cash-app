package com.nnoytra.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserContactRequest {
	private String phone, email;
	private AddressRequest addressRequest;
}
