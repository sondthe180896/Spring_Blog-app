package com.spr.blogapp.mapper;

import com.spr.blogapp.dto.UserDto;
import com.spr.blogapp.entity.User;
import com.spr.blogapp.form.UserCreateForm;

public class UserMapper {
    public static User map(UserCreateForm form){
        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());
        return user;
    }
    public static UserDto map(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        return userDto;
    }
}
