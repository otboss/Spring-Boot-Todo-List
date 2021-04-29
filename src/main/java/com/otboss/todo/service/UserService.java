package com.otboss.todo.service;

import com.otboss.todo.dto.UserDto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService extends JpaRepository<UserDto, Long> {

}
