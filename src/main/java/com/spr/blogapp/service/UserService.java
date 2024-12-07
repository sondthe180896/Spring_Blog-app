package com.spr.blogapp.service;

import com.spr.blogapp.dto.UserDto;
import com.spr.blogapp.form.UserCreateForm;

public interface UserService {
    UserDto create(UserCreateForm form);
}
