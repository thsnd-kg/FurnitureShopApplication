package com.furnitureshop.product.dto.variant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateVariantValueDto {
    @NotNull(message = "Option id must not be null")
    private Long optionId;

    @NotBlank(message = "Option value must not be blank")
    private String optionValue;
}
