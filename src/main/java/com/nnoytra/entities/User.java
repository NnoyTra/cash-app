package com.nnoytra.entities;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails{

	private static final long serialVersionUID = 6396547286902529797L;
	@Id @GeneratedValue
	private Long id;
	@Column(unique = true, nullable = false, updatable = false)
	private String userID;
	@Column(unique = true, nullable = false, updatable = true)
	private String username;
	@Column(unique = true, nullable = false, updatable = true)
	private String password;
	private boolean isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private UserContact userContact;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getAuthority());
		return Arrays.asList(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

}
