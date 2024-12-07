package com.spr.blogapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {PostIdExistValidator.class}
)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface PostIdExist {
    String message() default "Bài viết không tồn tại với ID đã nhập";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

