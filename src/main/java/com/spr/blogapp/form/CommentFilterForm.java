package com.spr.blogapp.form;

import com.spr.blogapp.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentFilterForm {
    private String search;
    private Long postId;
    private Comment.Status status;
}
