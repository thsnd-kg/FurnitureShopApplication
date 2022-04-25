package com.furnitureshop.product.dto.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateOptionDto {
    @NotBlank(message = "Option name must not be blank")
    @Size(max = 100, message = "Option name must be less than 100 characters")
    private String optionName;
}
