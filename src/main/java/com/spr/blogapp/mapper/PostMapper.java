package com.spr.blogapp.mapper;

import com.spr.blogapp.dto.PostDto;
import com.spr.blogapp.entity.Post;
import com.spr.blogapp.form.PostCreateForm;
import com.spr.blogapp.form.PostUpdateForm;

public class PostMapper {
    public static Post map(PostCreateForm form){
        var post = new Post();
        post.setTitle(form.getTitle());
        post.setDescription(form.getDescription());
        post.setContent(form.getContent());
        return post;
    }
    public static PostDto map(Post post){
        var dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        return dto.withSelRel();
    }

    public static Object map(PostUpdateForm form) {
        var post = new Post();
        post.setTitle(form.getTitle());
        post.setDescription(form.getDescription());
        post.setContent(form.getContent());
        return post;
    }

    public static void map(PostUpdateForm form, Post post) {
        post.setTitle(form.getTitle());
        post.setDescription(form.getDescription());
        post.setContent(form.getContent());
    }
}
