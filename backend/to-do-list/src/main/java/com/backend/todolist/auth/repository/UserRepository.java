package com.backend.todolist.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.todolist.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}