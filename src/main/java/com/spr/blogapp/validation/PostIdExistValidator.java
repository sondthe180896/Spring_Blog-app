package com.spr.blogapp.validation;

import com.spr.blogapp.reporisoty.PostRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostIdExistValidator implements ConstraintValidator<PostIdExist, Long> {
    private PostRepository postRepository;
    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return postRepository.existsById(id);
    }
}
