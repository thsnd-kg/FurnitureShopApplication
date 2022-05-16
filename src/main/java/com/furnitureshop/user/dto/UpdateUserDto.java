package com.furnitureshop.user.dto;

import com.furnitureshop.user.validation.annotation.UniqueEmail;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class UpdateUserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String address;
    private String imgUrl;
    private Long roleId;

    @Email
    @UniqueEmail
    private String email;
}
