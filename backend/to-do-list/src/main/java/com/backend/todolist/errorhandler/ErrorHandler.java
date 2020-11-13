package com.backend.todolist.errorhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorHandler {
	private Date timestamp;
	private String message;
	private HttpStatus status;
	
	public ErrorHandler(String message, HttpStatus status) {
		super();
		this.timestamp = new Date();
		this.message = message;
		this.status = status;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return "ErrorHandler [timestamp=" + timestamp + ", message=" + message + ", status=" + status + "]";
	}

	public ResponseEntity<Object> handler() {
		return new ResponseEntity<>(this, this.status);
	}
	
	
}
