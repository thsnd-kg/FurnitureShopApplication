package com.furnitureshop.product.dto.variant;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
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
    private List<UpdateValueDto> values;
}
