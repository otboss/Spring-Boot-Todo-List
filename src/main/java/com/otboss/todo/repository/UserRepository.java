package com.otboss.todo.repository;

import java.util.Optional;

import com.otboss.todo.model.User;
import com.otboss.todo.repository.contracts.UserService;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements UserService {

    public UserRepository() {
        // this.repository = new HashMap<>();
    }

    @Override
    public void store(User t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
    }

    @Override
    public Optional<User> getUser(int id) {
        // TODO Auto-generated method stub
        final String sql = "SELECT * FROM ";
        return null;
    }

}
