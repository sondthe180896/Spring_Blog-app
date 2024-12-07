package com.spr.blogapp.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CommentCreateForm {

    @NotBlank(message = "Tên không được để trống!")
    @Length(message = "Tên không được quá 100 ký tự", max = 100)
    private String name;

    @NotBlank(message = "Email không được để trống!")
    @Length(message = "Email không được quá 80 ký tự", max = 80)
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Body không được để trống!")
    @Length(message = "Body không được quá 200 ký tự", max = 200)
    private String body;

//    @NotNull(message = "Status không được để trống!")
//    @ValidateCommentStatus
    @Pattern(regexp = "REVIEW|OPEN|CLOSE", message = "Status phải là REVIEW, OPEN, CLOSE")
    private String status;


}
