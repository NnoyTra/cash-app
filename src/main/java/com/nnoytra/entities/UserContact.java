package com.nnoytra.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserContact {

	@Id @GeneratedValue
	private Long id;
	
	@Column(unique = true, nullable = false, updatable = true, length = 10)
	private String phone;
	
	@Column(unique = true, nullable = false, updatable = true)
	private String email;
	
	@OneToOne(mappedBy = "userContact")
	private User user;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Address address;
	
	
	
}
