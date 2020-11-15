package com.backend.todolist.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.todolist.service.TodoService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class TodoController {
	@Autowired
	private TodoService todoService;
	
	@RequestMapping(value = "/api/todo", method = RequestMethod.POST)
	public ResponseEntity<Object> createTodo(@Valid @RequestBody TodoCreateRequest todoCreateRequest, Principal principal) {
		return new ResponseEntity<>(todoService.create(todoCreateRequest, principal.getName()), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/todo", method = RequestMethod.GET)
	public ResponseEntity<Object> todoReadAll(Principal principal){
		return new ResponseEntity<>(todoService.readAll(principal.getName()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/todo/{_id}", method = RequestMethod.GET)
	public ResponseEntity<Object> todoRead(@PathVariable long _id, Principal principal) {
		return new ResponseEntity<>(todoService.readById(_id, principal.getName()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/todo/{_id}/markcomplete", method = RequestMethod.PUT)
	public ResponseEntity<Object> markCompleteTodo(@PathVariable long _id, Principal principal) {
		return new ResponseEntity<>(todoService.markCompleteById(_id, principal.getName()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/todo/{_id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateTodo(@PathVariable long _id, @Valid @RequestBody TodoUpdateRequest todoUpdateRequest, Principal principal) {
		return new ResponseEntity<>(todoService.updateById(_id, todoUpdateRequest, principal.getName()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/todo/{_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteTodo(@PathVariable long _id, Principal principal) {
		todoService.deleteById(_id, principal.getName());
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
