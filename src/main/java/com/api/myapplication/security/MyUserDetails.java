package com.api.myapplication.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.myapplication.entity.User;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private User user;
	private Collection<? extends GrantedAuthority> authorities;

	public MyUserDetails(User user) {
		this.user = user;
		this.authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getAuthority().name())).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.isEnabled();
	}
	
	public Long getId() {
		return user.getId();
	}

}
