package com.backend.todolist.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.todolist.errorhandler.CustomException;
import com.backend.todolist.model.Todo;
import com.backend.todolist.service.TodoService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@ApiResponses(value = {
        @ApiResponse(code=400, message = "Bad Request", response = CustomException.class),
        @ApiResponse(code=401, message = "Unauthorized", response = CustomException.class),
        @ApiResponse(code=403, message = "Forbidden", response = CustomException.class),
        @ApiResponse(code=404, message = "Not Found", response = CustomException.class)
 })
public class TodoController {
	@Autowired
	private TodoService todoService;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value = "/api/todo", method = RequestMethod.POST)
	public ResponseEntity<Todo> createTodo(@Valid @RequestBody TodoCreateRequest todoCreateRequest, Principal principal) {
		return new ResponseEntity<>(todoService.create(todoCreateRequest, principal.getName()), HttpStatus.CREATED);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/api/todo", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> todoReadAll(Principal principal){
		return new ResponseEntity<>(todoService.readAll(principal.getName()), HttpStatus.OK);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/api/todo/{_id}", method = RequestMethod.GET)
	public ResponseEntity<Todo> todoRead(@PathVariable long _id, Principal principal) {
		return new ResponseEntity<>(todoService.readById(_id, principal.getName()), HttpStatus.OK);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/api/todo/{_id}/markcomplete", method = RequestMethod.PUT)
	public ResponseEntity<Todo> markCompleteTodo(@PathVariable long _id, Principal principal) {
		return new ResponseEntity<>(todoService.markCompleteById(_id, principal.getName()), HttpStatus.OK);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/api/todo/{_id}", method = RequestMethod.PUT)
	public ResponseEntity<Todo> updateTodo(@PathVariable long _id, @Valid @RequestBody TodoUpdateRequest todoUpdateRequest, Principal principal) {
		return new ResponseEntity<>(todoService.updateById(_id, todoUpdateRequest, principal.getName()), HttpStatus.OK);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/api/todo/{_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteTodo(@PathVariable long _id, Principal principal) {
		todoService.deleteById(_id, principal.getName());
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
