package com.backend.todolist.errorhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomException {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    
	public CustomException(LocalDateTime timestamp, HttpStatus status, String message) {
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CustomException [timestamp=" + timestamp + ", status=" + status + ", message=" + message + "]";
	}
	
}
