package com.furnitureshop.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmMailDto {
    private String email;
    private String token;
}
