package com.otboss.todo.service;

import javax.transaction.Transactional;

import com.otboss.todo.repository.TodoListRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TodoListService extends TodoListRepository {
    @Override
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update TodoListItem u set u.entry = :entry, u.checked = :checked where u.id = :id")
    public void updateById(Long id, String entry, Boolean checked);
}
