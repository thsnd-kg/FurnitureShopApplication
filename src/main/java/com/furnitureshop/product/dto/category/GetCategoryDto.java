package com.furnitureshop.product.dto.category;

import com.furnitureshop.product.entity.Category;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetCategoryDto {
    private Long categoryId;
    private String categoryName;
    private String categoryDesc;
    private List<GetOptionDto> options;

    public GetCategoryDto(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.categoryDesc = category.getCategoryDesc();
        this.options = category.getOptions().stream().map(GetOptionDto::new).collect(Collectors.toList());
    }
}
