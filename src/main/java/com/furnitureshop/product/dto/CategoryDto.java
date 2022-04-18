package com.furnitureshop.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class CategoryDto {
    @NotNull(message = "Category id must not be null")
    private Long categoryId;

    @NotBlank(message = "Category name must be not blank")
    private String categoryName;

    @Size(max = 100, message = "Category description must be less than 100 characters")
    private String categoryDescription;

    @NotNull(message = "List option must be not null")
    private List<OptionDto> optionDtos;

    private Long parentId;
}
