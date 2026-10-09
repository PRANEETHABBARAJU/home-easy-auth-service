package com.springtasks.www.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springtasks.www.Entity.CustomUserDetails;
import com.springtasks.www.Entity.LoginEntity;
import com.springtasks.www.ExceptionHandling.InvalidCredentialsException;
import com.springtasks.www.Repository.LoginRepository;

@Service
public class CustomUserDetailsService  implements UserDetailsService{
	private final LoginRepository loginRepository;
	public CustomUserDetailsService(LoginRepository loginRepository) {
		this.loginRepository= loginRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginEntity  login = loginRepository.findByUserName(username).orElseThrow(()-> new InvalidCredentialsException("Invalid Username or Password"));
		return new CustomUserDetails(login);
	}

}
