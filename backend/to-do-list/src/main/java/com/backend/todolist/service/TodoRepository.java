package com.backend.todolist.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.todolist.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
	
}
