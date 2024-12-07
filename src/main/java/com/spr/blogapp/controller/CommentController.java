package com.spr.blogapp.controller;

import com.spr.blogapp.dto.CommentDto;
import com.spr.blogapp.form.CommentCreateForm;
import com.spr.blogapp.form.CommentFilterForm;
import com.spr.blogapp.form.CommentUpdateForm;
import com.spr.blogapp.service.CommentService;
import com.spr.blogapp.validation.PostIdExist;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Validated
public class CommentController {
    private CommentService commentService;

    @PostMapping("/api/v1/post/{postId}/comments")
    public CommentDto createComment(@Valid
            @RequestBody CommentCreateForm form,
            @PathVariable("postId") Long postId) {
        return commentService.commentCreate(form,postId);
    }

    @GetMapping("/api/v1/comments")
    public Page<CommentDto> findAll(CommentFilterForm form, Pageable pageable) {
        return commentService.findAll(form, pageable);
    }

    @GetMapping("/api/v1/post/comments/{commentId}")
    public CommentDto findById(@PathVariable("commentId") String commentId) {
        return commentService.findById(commentId);
    }

    @GetMapping("/api/v1/post/{postId}/comments")
    public Page<CommentDto> findByPostId(
            @PathVariable("postId") @PostIdExist Long postId, Pageable pageable){
        return commentService.findByPostId(postId, pageable);
    }

    @PutMapping("/api/v1/post/comments/{commentId}")
    public CommentDto update(@RequestBody CommentUpdateForm form, @PathVariable("commentId") String id){
        return commentService.update(form,id);
    }

    @DeleteMapping("/api/v1/post/comments/{commentId}")
    public void delete(@PathVariable("commentId") String id){
        commentService.deleteById(id);
    }
}
