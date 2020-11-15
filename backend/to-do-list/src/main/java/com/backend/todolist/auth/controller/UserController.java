package com.backend.todolist.auth.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.todolist.auth.jwt.JwtTokenGenerator;
import com.backend.todolist.auth.repository.UserRepository;
import com.backend.todolist.auth.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserController {	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
    PasswordEncoder passwordEncoder;
    
	@Autowired
    JwtTokenGenerator jwtTokenGenerator;
    
	@Autowired
    UserRepository userRepository;
	
	@Autowired
	UserService userService;
    
	@RequestMapping(value = "/api/auth/signin", method = RequestMethod.POST)
    public ResponseEntity<Object> signin(@Valid @RequestBody UserSigninRequest userSigninRequest) {
		return new ResponseEntity<>(userService.signin(userSigninRequest), HttpStatus.OK);
    }
	
	@RequestMapping(value = "/api/auth/signup", method = RequestMethod.POST)
    public ResponseEntity<Object> signup(@Valid @RequestBody UserSignupRequest userSignupRequest) {
		return new ResponseEntity<>(userService.signup(userSignupRequest), HttpStatus.OK);
    }
}
