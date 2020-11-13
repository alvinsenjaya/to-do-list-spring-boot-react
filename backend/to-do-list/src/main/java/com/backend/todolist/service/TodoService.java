package com.backend.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.todolist.errorhandler.ResourceNotFoundException;
import com.backend.todolist.model.Todo;
import com.backend.todolist.model.TodoRepository;

@Component
public class TodoService {
	@Autowired
	private TodoRepository todoRepository;
	
	public Todo create(Todo todo) {
		return todoRepository.save(todo);
	}
	
	public Todo readById(long _id) {
		Optional<Todo> todo = todoRepository.findById(_id);
		if(!todo.isPresent()) {
			throw new ResourceNotFoundException("Todo not found");
		}
		return todo.get();
	}
	
	public List<Todo> readAll( ) {
		return todoRepository.findAll();
	}
	
	public void deleteById(long _id) {
		Optional<Todo> todoData = todoRepository.findById(_id);
		if(!todoData.isPresent()) {
			throw new ResourceNotFoundException("Todo not found");
		}
		todoRepository.deleteById(_id);
	}
	
	public Todo updateById(long _id, Todo todo) {
		Optional<Todo> todoData = todoRepository.findById(_id);
		if(!todoData.isPresent()) {
			throw new ResourceNotFoundException("Todo not found");
		}
		
		Todo _todo = todoData.get();
		_todo.setTitle(todo.getTitle());
		_todo.setTargetDate(todo.getTargetDate());
		return todoRepository.save(_todo);
	}
	
	public Todo markCompleteById(long _id) {
		Optional<Todo> todo = todoRepository.findById(_id);
		if(!todo.isPresent()) {
			throw new ResourceNotFoundException("Todo not found");
		}
		
		Todo _todo = todo.get();
		_todo.setIsCompleted(!_todo.getIsCompleted());
		return todoRepository.save(_todo);
	}
}
