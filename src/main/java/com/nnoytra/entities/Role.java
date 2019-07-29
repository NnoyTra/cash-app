package com.nnoytra.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements GrantedAuthority{

	private static final long serialVersionUID = 3778817647997085388L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String role;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<UserRole> userRoles;	
	
	@Override
	public String getAuthority() {
		return role;
	}

}
