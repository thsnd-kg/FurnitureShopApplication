package com.furnitureshop.product.dto.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class CreateCategoryDto {
    @NotNull(message = "Category name must not be null")
    @Size(min = 1, max = 50, message = "Category name must be less than 50 characters")
    private String categoryName;

    @Size(max = 100, message = "Category description must be less than 100 characters")
    private String categoryDesc;

    @NotNull(message = "List option must not be null")
    @NotEmpty(message = "List option must not be empty")
    private List<@Valid CreateOptionDto> options;

    private Long parentId;
}
