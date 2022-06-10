package com.furnitureshop.product.dto.category;

import com.furnitureshop.product.dto.product.GetProductDto;
import com.furnitureshop.product.entity.Category;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetCategoryDto {
    private final Long categoryId;
    private final String categoryName;
    private final String categoryDesc;
    private final Boolean isDeleted;
    private final Long parentId;
    private final List<GetOptionDto> options;

    public GetCategoryDto(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.categoryDesc = category.getCategoryDesc();
        this.isDeleted = category.getIsDeleted();
        this.options = category.getOptions().isEmpty() ? new ArrayList<>() : category.getOptions().stream().map(GetOptionDto::new).collect(Collectors.toList());
        this.options.sort(Comparator.comparing(GetOptionDto::getOptionId));
        this.parentId = category.getParent() != null ? category.getParent().getCategoryId() : null;
    }
}
