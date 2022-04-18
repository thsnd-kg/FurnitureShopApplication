package com.furnitureshop.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VariantValueDto {
    @NotNull(message = "Product id must not be null")
    private Long productId;

    @NotNull(message = "Variant id must not be null")
    private Long variantId;

    @NotNull(message = "Option id must not be null")
    private Long optionId;

    @NotNull(message = "Category id must not be null")
    private Long categoryId;

    @NotBlank(message = "Option value must not be blank")
    private String optionValue;
}
