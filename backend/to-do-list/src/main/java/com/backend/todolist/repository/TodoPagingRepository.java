package com.backend.todolist.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.backend.todolist.model.Todo;

@Repository
public interface TodoPagingRepository extends PagingAndSortingRepository<Todo, Long> {
	List<Todo> findAllByUsername(String username, Pageable pageable);
	List<Todo> findAllByUsernameAndIsCompleted(String username, boolean isCompleted, Pageable pageable);
}
