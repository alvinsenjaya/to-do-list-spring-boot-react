package com.backend.todolist.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.todolist.auth.controller.UserSigninRequest;
import com.backend.todolist.auth.controller.UserSigninResponse;
import com.backend.todolist.auth.controller.UserSignupRequest;
import com.backend.todolist.auth.controller.UserSignupResponse;
import com.backend.todolist.auth.jwt.JwtTokenGenerator;
import com.backend.todolist.auth.model.User;
import com.backend.todolist.auth.repository.UserRepository;
import com.backend.todolist.errorhandler.BadRequestException;

@Service
public class UserService {
	@Autowired
    UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
    PasswordEncoder passwordEncoder;
    
	@Autowired
    JwtTokenGenerator jwtTokenGenerator;
	
	public UserSignupResponse signup(UserSignupRequest userSignupRequest) {
		try {
			String username = userSignupRequest.getUsername();
	        String password = userSignupRequest.getPassword();
	        
	        User user =  userRepository.findByUsername(username);
	        if(user != null) {
	        	throw new BadRequestException("Username is already exist");
	        }
	        
	        User _user = new User(username, passwordEncoder.encode(password));
	        _user = userRepository.save(_user);
	        
	        String token = jwtTokenGenerator.createToken(_user.getUsername(), _user.getRoleAsList());
	        
			return new UserSignupResponse(username, token);
		} catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password");
        }
	}
	
	public UserSigninResponse signin(UserSigninRequest userSigninRequest) {
		try {
			String username = userSigninRequest.getUsername();
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userSigninRequest.getPassword()));
	        String token = jwtTokenGenerator.createToken(username, this.userRepository.findByUsername(username).getRoleAsList());
	        
			return new UserSigninResponse(username, token);
		} catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password");
        }
	}
}
