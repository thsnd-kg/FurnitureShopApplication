package com.furnitureshop.product.dto.variant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Collection;

@Getter
public class CreateVariantDto {
    @NotNull(message = "{product.not-null}")
    private Long productId;

    @Size(max = 300, message = "{variant.image.size}")
    private String image;

    @Positive(message = "Price must be greater than 0")
    private Integer price;

    @Size(max = 50, message = "{variant.sku.size}")
    private String sku;

    @NotNull(message = "Values must not be null")
    @NotEmpty(message = "Values must not be empty")
    private Collection<@Valid CreateValueDto> values;
}
