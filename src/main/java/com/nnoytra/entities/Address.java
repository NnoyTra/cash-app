package com.nnoytra.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String state, city, street, zipCode;
	
	@JsonIgnore
	@OneToOne(mappedBy = "address")
	private UserContact userContact;
}
