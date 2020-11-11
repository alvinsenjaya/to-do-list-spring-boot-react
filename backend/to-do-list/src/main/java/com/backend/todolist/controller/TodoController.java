package com.backend.todolist.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.todolist.ErrorHandler.ErrorHandler;
import com.backend.todolist.entity.Todo;
import com.backend.todolist.service.TodoRepository;

@RestController
@CrossOrigin(origins = "*")
public class TodoController {
	@Autowired
	private TodoRepository todoRepository;
	
	@RequestMapping(value = "/api/todo", method = RequestMethod.GET)
	public ResponseEntity<Object> readAllTodo(){
		return new ResponseEntity<>(todoRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/todo/{_id}", method = RequestMethod.GET)
	public ResponseEntity<Object> readTodo(@PathVariable long _id) {
		Optional<Todo> todo = todoRepository.findById(_id);
		if(!todo.isPresent()) {
			return new ErrorHandler("Todo not found", HttpStatus.NOT_FOUND).handler();
		}
		return new ResponseEntity<>(todo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/todo/{_id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> markCompleteTodo(@PathVariable long _id) {
		Optional<Todo> todo = todoRepository.findById(_id);
		if(!todo.isPresent()) {
			return new ErrorHandler("Todo not found", HttpStatus.NOT_FOUND).handler();
		}
		Todo _todo = todo.get();
		_todo.setIsCompleted(!_todo.getIsCompleted());
		
		return new ResponseEntity<>(todoRepository.save(_todo), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/todo", method = RequestMethod.POST)
	public ResponseEntity<Object> createTodo(@RequestBody Todo todo) {
		return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/todo/{_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteTodo(@PathVariable long _id) {
		todoRepository.deleteById(_id);		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
