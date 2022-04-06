package com.furnitureshop.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CategoryDto {
    private Long categoryId;

    @NotEmpty
    @NotBlank
    private String categoryName;

    private String description;
    private Long parentId;
}
