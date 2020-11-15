package com.backend.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.todolist.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
	@Query("select u from Todo u where u.username = ?1")
	List<Todo> findByUsername(String username);
	
	@Query("select u from Todo u where u.username = ?1 and u._id = ?2")
	Todo findByUsernameAnd_id(String username, long _id);
}
