package com.otboss.todo.repository;

import java.util.List;

import com.otboss.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByEmail(String email);
}
