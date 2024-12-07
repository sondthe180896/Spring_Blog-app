package com.spr.blogapp.service;

import com.spr.blogapp.dto.PostDto;
import com.spr.blogapp.form.PostCreateForm;
import com.spr.blogapp.form.PostFilterForm;
import com.spr.blogapp.form.PostUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();
    Page<PostDto> findByTitleLikeOrContentLike(String title, String content,Pageable pageable);
    PostDto create(PostCreateForm form);
    PostDto findById(Long id);
    PostDto update(PostUpdateForm form, Long id);
    void delete(Long id);
    Page<PostDto> findByPage(PostFilterForm form, Pageable pageable);
    void deleteByTitle(String title);
    void deleteByDesc(String description);
}
