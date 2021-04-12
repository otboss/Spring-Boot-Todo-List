package com.otboss.todo.repository;

import java.util.List;

import com.otboss.todo.model.TodoListItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoListItem, Long> {
    public List<TodoListItem> findByUserId(Long userId);
}
