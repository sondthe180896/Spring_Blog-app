package com.spr.blogapp.service.impl;

import com.spr.blogapp.dto.PostDto;
import com.spr.blogapp.entity.Post;
import com.spr.blogapp.form.PostCreateForm;
import com.spr.blogapp.form.PostFilterForm;
import com.spr.blogapp.form.PostUpdateForm;
import com.spr.blogapp.mapper.PostMapper;
import com.spr.blogapp.reporisoty.PostRepository;
import com.spr.blogapp.service.PostService;
import com.spr.blogapp.specification.PostSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Override
    public List<PostDto> findAll() {
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            var dto = PostMapper.map(post);
            postDtos.add(dto);
        }
        return postDtos;
    }

    @Override
    public Page<PostDto> findByTitleLikeOrContentLike(String title, String content, Pageable pageable) {
        return postRepository.findByTitleLikeOrContentLike(title, content, pageable).map(PostMapper::map);
    }

    @Override
    public PostDto create(PostCreateForm form) {
        var post = PostMapper.map(form);
        var savePost = postRepository.save(post);
        return PostMapper.map(savePost);
    }

    @Override
    public PostDto findById(Long id) {
        var post = postRepository.findById(id).get();
        return PostMapper.map(post);
    }

    @Override
    public PostDto update(PostUpdateForm form, Long id) {
        var post = postRepository.findById(id).get();
        PostMapper.map(form, post);
        var savedPost = postRepository.save(post);
        return PostMapper.map(savedPost);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<PostDto> findByPage(PostFilterForm form, Pageable pageable) {
        var spec = PostSpecification.buildSpec(form);
        return postRepository.findAll(spec, pageable).map(PostMapper::map);
    }

    @Override
    @Transactional
    public void deleteByTitle(String title) {
        postRepository.deleteByTitle(title);
    }

    @Override
    @Transactional
    public void deleteByDesc(String description) {
        postRepository.deleteByDesc(description);
    }
}
