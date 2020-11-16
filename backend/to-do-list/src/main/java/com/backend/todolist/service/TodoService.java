package com.backend.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.backend.todolist.controller.CountResponse;
import com.backend.todolist.controller.TodoCreateRequest;
import com.backend.todolist.controller.TodoUpdateRequest;
import com.backend.todolist.errorhandler.BadRequestException;
import com.backend.todolist.errorhandler.InvalidPageException;
import com.backend.todolist.errorhandler.ResourceNotFoundException;
import com.backend.todolist.model.Todo;
import com.backend.todolist.repository.TodoRepository;
import com.backend.todolist.repository.TodoPagingRepository;

@Service
public class TodoService {
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private TodoPagingRepository todoPagingRepository;
	
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
	
	public List<Todo> readAllPageable(String username, String pageNumber, String pageSize) {
		int _pageNumber = pageNumberStringToInteger(pageNumber);
		int _pageSize = pageSizeStringToInteger(pageSize);
		
		Pageable pageable = PageRequest.of(_pageNumber, _pageSize, Sort.by(Sort.Direction.ASC, "targetDate"));
		return todoPagingRepository.findAllByUsername(username, pageable);
	}
	
	public List<Todo> readAllByIsCompleted(String username, String isCompleted) {
		boolean _isCompleted = isCompletedStringToBoolean(isCompleted);
		return todoRepository.findAllByUsernameAndIsCompleted(username, _isCompleted);
	}
	
	public List<Todo> readAllByIsCompletedPageable(String username, String isCompleted, String pageNumber, String pageSize) {
		boolean _isCompleted = isCompletedStringToBoolean(isCompleted);
		int _pageNumber = pageNumberStringToInteger(pageNumber);
		int _pageSize = pageSizeStringToInteger(pageSize);
		
		Pageable pageable = PageRequest.of(_pageNumber, _pageSize, Sort.by(Sort.Direction.ASC, "targetDate"));
		return todoPagingRepository.findAllByUsernameAndIsCompleted(username, _isCompleted, pageable);
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
	
	public CountResponse countAll(String username) {
		return new CountResponse(todoRepository.countByUsername(username));
	}
	
	public CountResponse countAllByIsCompleted(String username, String isCompleted) {
		boolean _isCompleted = isCompletedStringToBoolean(isCompleted);
		return new CountResponse(todoRepository.countByUsernameAndIsCompleted(username, _isCompleted));
	}
	
	private boolean isCompletedStringToBoolean(String isCompleted) {
		try {
			return Boolean.parseBoolean(isCompleted);  
		} catch (Exception e) {
			throw new BadRequestException("Invalid isCompleted");
		}
	}
	
	private int pageNumberStringToInteger(String pageNumber) {
		int _pageNumber;
		
		try {
			_pageNumber = Integer.parseInt(pageNumber);
		} catch(Exception e) {
			throw new InvalidPageException("Invalid Page Number");
		}
		
		if(_pageNumber < 0) {
			throw new InvalidPageException("Invalid page number");
		}
		
		return _pageNumber;
	}
	
	private int pageSizeStringToInteger(String pageSize) {
		int _pageSize;
		
		try {
			_pageSize = Integer.parseInt(pageSize);
		} catch(Exception e) {
			throw new InvalidPageException("Invalid Page Size");
		}
		
		if(_pageSize < 1) {
			throw new InvalidPageException("Invalid page size");
		}
		
		return _pageSize;
	}
}
