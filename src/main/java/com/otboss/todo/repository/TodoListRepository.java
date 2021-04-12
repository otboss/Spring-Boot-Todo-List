package com.otboss.todo.repository;

import com.otboss.todo.model.TodoListItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoListItem, Long> {
}
