package com.backend.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.todolist.controller.TodoCreateRequest;
import com.backend.todolist.controller.TodoUpdateRequest;
import com.backend.todolist.errorhandler.ResourceNotFoundException;
import com.backend.todolist.model.Todo;
import com.backend.todolist.repository.TodoRepository;

@Service
public class TodoService {
	@Autowired
	private TodoRepository todoRepository;
	
	public Todo create(TodoCreateRequest todoCreateRequest, String username) {
		Todo todo = new Todo(todoCreateRequest.getTitle(), todoCreateRequest.getTargetDate(), username);
		return todoRepository.save(todo);
	}
	
	public Todo readById(long _id, String username) {
		Todo todo = todoRepository.findByUsernameAnd_id(username, _id);
		if(todo == null) {
			throw new ResourceNotFoundException("Todo not found");
		}
		return todo;
	}
	
	public List<Todo> readAll(String username) {
		return todoRepository.findByUsername(username);
	}
	
	public void deleteById(long _id, String username) {
		Todo todo = todoRepository.findByUsernameAnd_id(username, _id);
		if(todo == null) {
			throw new ResourceNotFoundException("Todo not found");
		}
		todoRepository.deleteById(_id);
	}
	
	public Todo updateById(long _id, TodoUpdateRequest todoUpdateRequest, String username) {
		Todo todo = todoRepository.findByUsernameAnd_id(username, _id);
		if(todo == null) {
			throw new ResourceNotFoundException("Todo not found");
		}
		
		todo.setTitle(todoUpdateRequest.getTitle());
		todo.setTargetDate(todoUpdateRequest.getTargetDate());
		return todoRepository.save(todo);
	}
	
	public Todo markCompleteById(long _id, String username) {
		Todo todo = todoRepository.findByUsernameAnd_id(username, _id);
		if(todo == null) {
			throw new ResourceNotFoundException("Todo not found");
		}
		
		todo.setIsCompleted(!todo.getIsCompleted());
		return todoRepository.save(todo);
	}
}
