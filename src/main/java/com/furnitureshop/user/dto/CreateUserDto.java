package com.furnitureshop.user.dto;

import com.furnitureshop.user.validation.annotation.ConfirmPassword;
import com.furnitureshop.user.validation.annotation.UniqueEmail;
import com.furnitureshop.user.validation.annotation.UniqueUsername;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ConfirmPassword
public class CreateUserDto {

    @NotBlank(message = "{user.username.not-blank}")
    @UniqueUsername
    private String username;

    @NotBlank(message = "{user.password.not-blank}")
    private String password;
    @NotBlank(message = "{user.confirm-password.not-blank}")
    private String confirmPassword;

    @NotBlank(message = "{user.email.not-blank}")
    @Email(message = "{user.email.valid}")
    @UniqueEmail
    private String email;

    private Long roleId;

}
