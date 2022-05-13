package com.furnitureshop.product.dto.variant;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
public class UpdateVariantDto {
    @NotNull(message = "Variant id must not be null")
    private Long variantId;

    @NotNull(message = "{variant.product.not-null}")
    private Long productId;

    @Size(max = 300, message = "{variant.image.size}")
    private String image;

    @Positive(message = "Price must be greater than 0")
    private Integer price;

    @Size(max = 50, message = "{variant.sku.size}")
    private String sku;

    @NotNull(message = "Values must not be null")
    @NotEmpty(message = "Values must not be empty")
    private List<@Valid UpdateValueDto> options;
}
