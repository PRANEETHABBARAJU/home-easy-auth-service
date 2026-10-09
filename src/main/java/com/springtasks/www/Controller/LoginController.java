package com.springtasks.www.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springtasks.www.Service.loginService;
import com.springtasks.www.dto.LoginRequest;
import com.springtasks.www.dto.LoginResponse;
import com.springtasks.www.dto.RegisterRequest;
import com.springtasks.www.dto.RegisterResponse;

@RestController
@RequestMapping("/Login")
@CrossOrigin(originPatterns = {
	    "http://localhost:*",
	    "http://127.0.0.1:*"
	})
public class LoginController {
	private loginService loginservice;
	public LoginController(loginService loginservice) {
		this.loginservice= loginservice;
	}
	@PostMapping("/Register")
	@ResponseStatus(HttpStatus.CREATED)
	public RegisterResponse register(
	        @RequestBody RegisterRequest request) {

	    return loginservice.register(request);
	}
	@PostMapping("/login")
	public LoginResponse login(
	        @RequestBody LoginRequest request) {

	    return loginservice.findUser(
	        request.getUserName(),
	        request.getPassword()
	    );
	}
	@GetMapping("/test")
	public String check() {
		return "Protected Working";
	}

}
