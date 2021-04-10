package com.otboss.todo.repository.contracts;

import java.util.Optional;

import com.otboss.todo.model.User;

public interface UserService {
    public void store(User user);

    public Optional<User> getUser(int id);

    public void delete(int id);
}
