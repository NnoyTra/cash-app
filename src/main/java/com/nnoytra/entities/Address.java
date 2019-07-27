package com.nnoytra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Address {

	@Id @GeneratedValue
	private Long id;
	@Column(nullable = false, updatable = true)
	private String state, city, street;
	@Column(length = 5, nullable = false, updatable = true)
	private String zipCode;
	
	@OneToOne(mappedBy = "address")
	private UserContact userContact;
}
