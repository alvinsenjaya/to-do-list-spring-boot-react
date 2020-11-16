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
	
	public Todo readById(long id, String username) {
		Todo todo = todoRepository.findByUsernameAndId(username, id);
		if(todo == null) {
			throw new ResourceNotFoundException("Todo not found");
		}
		return todo;
	}
	
	public List<Todo> readAll(String username) {
		return todoRepository.findAllByUsername(username);
	}
	
	public void deleteById(long id, String username) {
		Todo todo = todoRepository.findByUsernameAndId(username, id);
		if(todo == null) {
			throw new ResourceNotFoundException("Todo not found");
		}
		todoRepository.deleteById(id);
	}
	
	public Todo updateById(long id, TodoUpdateRequest todoUpdateRequest, String username) {
		Todo todo = todoRepository.findByUsernameAndId(username, id);
		if(todo == null) {
			throw new ResourceNotFoundException("Todo not found");
		}
		
		todo.setTitle(todoUpdateRequest.getTitle());
		todo.setTargetDate(todoUpdateRequest.getTargetDate());
		return todoRepository.save(todo);
	}
	
	public Todo markCompleteById(long id, String username) {
		Todo todo = todoRepository.findByUsernameAndId(username, id);
		if(todo == null) {
			throw new ResourceNotFoundException("Todo not found");
		}
		
		todo.setIsCompleted(!todo.getIsCompleted());
		return todoRepository.save(todo);
	}
}
