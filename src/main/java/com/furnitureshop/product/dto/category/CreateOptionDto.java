package com.furnitureshop.product.dto.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class CreateOptionDto {
    @NotNull(message = "Option name must not be null")
    @Size(min = 1, max = 100, message = "Option name must be less than 100 characters")
    private String optionName;
}
