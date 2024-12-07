package com.spr.blogapp.form;

import com.spr.blogapp.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserCreateForm {
    private String name;
    private String email;
    private String username;
    private String password;
}
