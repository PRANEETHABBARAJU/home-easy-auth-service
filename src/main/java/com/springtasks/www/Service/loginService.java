package com.springtasks.www.Service;


import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springtasks.www.Entity.LoginEntity;
import com.springtasks.www.ExceptionHandling.InvalidCredentialsException;
import com.springtasks.www.Repository.LoginRepository;
import com.springtasks.www.Security.JwtService;
import com.springtasks.www.dto.LoginResponse;
import com.springtasks.www.dto.RegisterRequest;
import com.springtasks.www.dto.RegisterResponse;

@Service
public class loginService {
	private final LoginRepository loginRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	public loginService(LoginRepository loginRepository , JwtService jwtService , PasswordEncoder passwordEncoder) {
		this.loginRepository = loginRepository;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
	}
	public LoginResponse findUser(
	        String userName,
	        String password) {

	    LoginEntity login =
	        loginRepository.findByUserName(userName)
	            .orElseThrow(() ->
	                new InvalidCredentialsException(
	                    "Invalid Username or Password"
	                )
	            );

	    if (!passwordEncoder.matches(
	            password,
	            login.getPassword())) {

	        throw new InvalidCredentialsException(
	            "Invalid Username or Password"
	        );
	    }

	    String token = jwtService.generateToken(
	        login.getUserId(),
	        login.getUserName(),
	        login.getRole()
	    );

	    return new LoginResponse(
	        token,
	        login.getUserId(),
	        login.getUserName(),
	        login.getFullName(),
	        login.getRole()
	    );
	}
	public RegisterResponse register(RegisterRequest request) {

	    if (loginRepository.findByUserName(request.getUserName()).isPresent()) {
	        throw new ResponseStatusException(
	            HttpStatus.CONFLICT,
	            "Username already exists"
	        );
	    }
	    
	    if (loginRepository.existsByPhone(
	            request.getPhone())) {

	        throw new ResponseStatusException(
	            HttpStatus.CONFLICT,
	            "Phone number already exists"
	        );
	    }

	    String role = request.getRole().trim().toUpperCase();

	    if (!role.equals("CUSTOMER") && !role.equals("PROVIDER")) {
	        throw new ResponseStatusException(
	            HttpStatus.BAD_REQUEST,
	            "Only CUSTOMER or PROVIDER registration is allowed"
	        );
	    }

	    LoginEntity user = new LoginEntity();

	    user.setFullName(request.getFullName());
	    user.setUserName(request.getUserName());
	    user.setPhone(request.getPhone());
	    user.setPassword(
	        passwordEncoder.encode(request.getPassword())
	    );
	    user.setRole(role);
	    user.setActive(true);

	    LoginEntity savedUser = loginRepository.save(user);

	    return new RegisterResponse(
	        savedUser.getUserId(),
	        savedUser.getUserName(),
	        savedUser.getFullName(),
	        savedUser.getPhone(),
	        savedUser.getRole(),
	        savedUser.isActive()
	    );
	}

}
