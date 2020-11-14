package com.backend.todolist.controller;

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

import com.backend.todolist.model.Todo;
import com.backend.todolist.service.TodoService;

@RestController
@CrossOrigin(origins = "*")
public class TodoController {
	@Autowired
	private TodoService todoService;
	
	@RequestMapping(value = "/api/todo", method = RequestMethod.GET)
	public ResponseEntity<Object> todoReadAll(){
		return new ResponseEntity<>(todoService.readAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/todo/{_id}", method = RequestMethod.GET)
	public ResponseEntity<Object> todoRead(@PathVariable long _id) {
		return new ResponseEntity<>(todoService.readById(_id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/todo/{_id}/markcomplete", method = RequestMethod.PUT)
	public ResponseEntity<Object> markCompleteTodo(@PathVariable long _id) {
		return new ResponseEntity<>(todoService.markCompleteById(_id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/todo/{_id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateTodo(@PathVariable long _id, @Valid @RequestBody Todo todo) {
		return new ResponseEntity<>(todoService.updateById(_id, todo), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/todo", method = RequestMethod.POST)
	public ResponseEntity<Object> createTodo(@Valid @RequestBody Todo todo) {
		return new ResponseEntity<>(todoService.create(todo), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/todo/{_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteTodo(@PathVariable long _id) {
		todoService.deleteById(_id);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
