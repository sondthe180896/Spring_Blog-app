package com.spr.blogapp.service.impl;

import com.spr.blogapp.dto.CommentDto;
import com.spr.blogapp.form.CommentCreateForm;
import com.spr.blogapp.form.CommentFilterForm;
import com.spr.blogapp.form.CommentUpdateForm;
import com.spr.blogapp.mapper.CommentMapper;
import com.spr.blogapp.reporisoty.CommentRepository;
import com.spr.blogapp.reporisoty.PostRepository;
import com.spr.blogapp.service.CommentService;
import com.spr.blogapp.specification.CommentSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Override
    public Page<CommentDto> findAll(CommentFilterForm form, Pageable pageable) {
        var spec  = CommentSpecification.buildSpec(form);
        return commentRepository.findAll(spec, pageable).map(CommentMapper::map);
    }

    @Override
    public Page<CommentDto> findByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable).map(CommentMapper::map);
    }

    @Override
    public CommentDto findById(String id) {
        return commentRepository.findById(id).map(CommentMapper::map).orElse(null);
    }

    @Override
    public CommentDto commentCreate(CommentCreateForm form, Long postId) {
        var comment = CommentMapper.map(form);
        var post = postRepository.findById(postId).get();
        comment.setPost(post);
        var savedComment = commentRepository.save(comment);
        return CommentMapper.map(savedComment);
    }

    @Override
    public CommentDto update(CommentUpdateForm form, String id) {
        var comment = commentRepository.findById(id).get();
        CommentMapper.map(form, comment);
        var updatedComment = commentRepository.save(comment);
        return CommentMapper.map(updatedComment);
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}
