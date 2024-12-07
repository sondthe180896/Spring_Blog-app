package com.spr.blogapp.controller;

import com.spr.blogapp.dto.PostDto;
import com.spr.blogapp.form.PostCreateForm;
import com.spr.blogapp.form.PostDeleteFormByTitle;
import com.spr.blogapp.form.PostFilterForm;
import com.spr.blogapp.form.PostUpdateForm;
import com.spr.blogapp.service.PostService;
import com.spr.blogapp.validation.PostIdExist;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Validated
public class PostController {
    private PostService postService;

//    @GetMapping("/api/v1/posts")
//    public List<PostDto> getAllPosts() {
//        return postService.findAll();
//    }

    @GetMapping("/api/v1/posts/{id}")
    public PostDto findById(@PathVariable("id") @PostIdExist Long id) {
        return postService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/posts")
    public PostDto create(@Valid @RequestBody PostCreateForm form) {
        return postService.create(form);
    }

    @PutMapping("/api/v1/posts/{id}")
    public PostDto update(@PostIdExist @Valid @RequestBody PostUpdateForm form,
                          @PathVariable("id") Long id) {
        return postService.update(form, id);
    }
    @DeleteMapping("/api/v1/posts/{id}")
    public void delete(@PostIdExist @PathVariable("id") Long id) {
        postService.delete(id);
    }

    @GetMapping("/api/v1/posts")
    public Page<PostDto> findByPage(PostFilterForm form, Pageable pageable) {
        return postService.findByPage(form, pageable);
    }

    @GetMapping("/api/v1/posts/{title}/{content}")
    public Page<PostDto> findByTitleLikeOrContentLike(@PathVariable("title") String title,
                                                      @PathVariable("title") String content,
                                                      Pageable pageable) {
        return postService.findByTitleLikeOrContentLike(
                "%" + title + "%",
                "%" + content + "%",
                pageable);
    }

    @DeleteMapping("/api/v1/post/title")
    public void deleteByTitle(@RequestBody PostDeleteFormByTitle form) {postService.deleteByTitle(form.getTitle());}

    @DeleteMapping("/api/v1/post/desc/{description}")
    public void deleteByDesc(@PathVariable("description") String description) {
        postService.deleteByDesc(description);
    }
}
