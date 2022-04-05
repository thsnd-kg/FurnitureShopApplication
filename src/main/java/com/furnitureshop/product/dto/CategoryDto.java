package com.furnitureshop.product.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {
    private Long categoryId;

    @NotEmpty
    @NotBlank
    private String categoryName;

    private String description;
    private Long parentId;
}
