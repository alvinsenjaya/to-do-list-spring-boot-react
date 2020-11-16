package com.backend.todolist.errorhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.validation.BindingResult;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
		CustomException err = new CustomException(LocalDateTime.now(), HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntityBuilder.build(err);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
		CustomException err = new CustomException(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntityBuilder.build(err);
	}
	
	@ExceptionHandler(InvalidPageException.class)
	public ResponseEntity<Object> handleInvalidPageException(InvalidPageException ex) {
		CustomException err = new CustomException(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntityBuilder.build(err);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> handleUsernameNotFoundException(BadCredentialsException ex) {
		CustomException err = new CustomException(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntityBuilder.build(err);
	}
	
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public ResponseEntity<Object> handleInvalidJwtAuthenticationException(InvalidJwtAuthenticationException ex) {
		CustomException err = new CustomException(LocalDateTime.now(), HttpStatus.UNAUTHORIZED, ex.getMessage());
		return ResponseEntityBuilder.build(err);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
		CustomException err = new CustomException(LocalDateTime.now(), HttpStatus.UNAUTHORIZED, "Expired or invalid JWT token");
		return ResponseEntityBuilder.build(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException (MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        
        List<String> errorMessage = new ArrayList<>();
        
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
            errorMessage.add(fieldError.getDefaultMessage());
        }
        
	    CustomException err = new CustomException(LocalDateTime.now(), HttpStatus.BAD_REQUEST, errorMessage.toString().substring(1, errorMessage.toString().length()-1));
		return ResponseEntityBuilder.build(err);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException (HttpMessageNotReadableException ex) {
	    CustomException err = new CustomException(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Invalid input");
		return ResponseEntityBuilder.build(err);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAll(Exception ex) {
		CustomException err = new CustomException(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, "Something happened");
		return ResponseEntityBuilder.build(err);
	}
}
