package com.nnoytra.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole {
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne()
	private User user;
	
	@ManyToOne()
	private Role role;

}
