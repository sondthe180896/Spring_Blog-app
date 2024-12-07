package com.spr.blogapp.form;

import com.spr.blogapp.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateForm {
    private String name;
    private String email;
    private String body;
    private String status;
}
