package com.spr.blogapp.controller;

import com.spr.blogapp.dto.UserDto;
import com.spr.blogapp.form.UserCreateForm;
import com.spr.blogapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/api/v1/users")
    public UserDto create(@RequestBody UserCreateForm form){
        return userService.create(form);
    }
    //

}
