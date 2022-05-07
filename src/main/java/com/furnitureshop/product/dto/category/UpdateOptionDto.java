package com.furnitureshop.product.dto.category;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UpdateOptionDto {
    @NotNull(message = "Option id must not be null")
    private Long optionId;

    @NotBlank(message = "Option name must not be blank")
    @Size(max = 100, message = "Option name must be less than 100 characters")
    private String optionName;
}
