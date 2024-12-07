package com.spr.blogapp.service;

import com.spr.blogapp.dto.CommentDto;
import com.spr.blogapp.form.CommentCreateForm;
import com.spr.blogapp.form.CommentFilterForm;
import com.spr.blogapp.form.CommentUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Page<CommentDto> findAll(CommentFilterForm form, Pageable pageable);
    Page<CommentDto> findByPostId(Long postId, Pageable pageable);
    CommentDto findById(String id);
    CommentDto commentCreate(CommentCreateForm form, Long postId);
    CommentDto update(CommentUpdateForm form, String id);
    void deleteById(String id);
}
