package com.furnitureshop.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class OptionDto {
    @NotNull(message = "Option id must not be null")
    private Long optionId;

    @NotNull(message = "Category id must not be null")
    private Long categoryId;

    @NotBlank(message = "Option name must not be blank")
    @Size(max = 100, message = "Option name must be less than 100 characters")
    private String optionName;
}
