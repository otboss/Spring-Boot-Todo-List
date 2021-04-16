package com.otboss.todo.repository;

import javax.transaction.Transactional;

import com.otboss.todo.model.TodoListItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TodoListRepository extends JpaRepository<TodoListItem, Long> {
    public Page<TodoListItem> findByUserId(Long userId, Pageable pageRequest);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update TodoListItem u set u.entry = :entry, u.checked = :checked where u.id = :id")
    public void updateById(Long id, String entry, Boolean checked);

    // @Modifying(clearAutomatically = true)
    // @Transactional
    // @Query("delete TodoListItem u where u.id = :id")
    // public void deleteById(Long id);
}
