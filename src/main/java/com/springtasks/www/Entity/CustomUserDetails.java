package com.springtasks.www.Entity;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singletonList(
				new SimpleGrantedAuthority("ROLE_" +loginEntity.getRole()));
	}
	private final LoginEntity loginEntity;
	public CustomUserDetails(LoginEntity loginEntity) {
		this.loginEntity = loginEntity;
	}

	@Override
	public @Nullable String getPassword() {
		
		return loginEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return loginEntity.getUserName();
	}
	@Override
	public boolean isEnabled() {
		return loginEntity.isActive();
	}
}
