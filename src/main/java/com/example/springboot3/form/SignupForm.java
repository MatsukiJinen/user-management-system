package com.example.springboot3.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class SignupForm {

    @NotBlank(groups = ValidateGroupRequired.class)
    @Email(groups = ValidateGroupContents.class)
    private String userId;

    @NotBlank(groups = ValidateGroupRequired.class)
    @Length(min = 4, max = 100, groups = ValidateGroupContents.class)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidateGroupContents.class)
    private String password;

    @NotBlank(groups = ValidateGroupRequired.class)
    private String userName;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(groups = ValidateGroupRequired.class)
    private Date birthday;

    @Min(value = 20, groups = ValidateGroupContents.class)
    @Max(value = 100, groups = ValidateGroupContents.class)
    private Integer age;

    @NotNull(groups = ValidateGroupRequired.class)
    private Integer gender;
}
