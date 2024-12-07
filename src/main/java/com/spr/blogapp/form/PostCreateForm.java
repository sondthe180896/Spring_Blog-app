package com.spr.blogapp.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter

public class PostCreateForm {
    @NotBlank(message = "{post.title.NotBlank.message}")
    @Length(max = 100, message = "{post.title.Length.message}")
    private String title;

    @NotBlank(message = "Mô tả bài viết không được để trống !")
    @Length(max = 100, message = "Mô tả bài viết tối đa 100 ký tự")
    private String description;

    @NotBlank(message = "Nội dung bài viết không được để trống !")
    @Length(max = 200, message = "Nội dung bài viết tối đa 200 ký tự")
    private String content;
}
