package com.furnitureshop.user.dto;

import com.furnitureshop.user.validation.annotation.FieldsValueMatch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@FieldsValueMatch(
        field = "newPassword",
        fieldMatch = "confirmPassword",
        message = "Confirm password does not match!"
)
public class ChangePasswordDto {
    private String username;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
