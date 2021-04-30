package com.otboss.todo.dto.mapper;

import com.otboss.todo.dto.UserDto;
import com.otboss.todo.model.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public static User toUser(UserDto userDto) {
        User user = new User(userDto.getEmail(), userDto.getPassword());
        return user;
    }
}
