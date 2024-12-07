package com.spr.blogapp.mapper;

import com.spr.blogapp.dto.CommentDto;
import com.spr.blogapp.entity.Comment;
import com.spr.blogapp.form.CommentCreateForm;
import com.spr.blogapp.form.CommentUpdateForm;

public class CommentMapper {
    public static Comment map(CommentCreateForm commentCreateForm) {
        var comment = new Comment();
        comment.setName(commentCreateForm.getName());
        comment.setEmail(commentCreateForm.getEmail());
        comment.setBody(commentCreateForm.getBody());
        comment.setStatus(commentCreateForm.getStatus());
        return comment;
    }
    public static CommentDto map(Comment comment) {
        var commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        commentDto.setStatus(comment.getStatus());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setUpdatedAt(comment.getUpdateAt());
        return commentDto.withSelfRel();
    }

    public static Comment map(CommentUpdateForm form, Comment comment) {
        comment.setName(form.getName());
        comment.setEmail(form.getEmail());
        comment.setBody(form.getBody());
        comment.setStatus(form.getStatus());
        return comment;
    }
}
