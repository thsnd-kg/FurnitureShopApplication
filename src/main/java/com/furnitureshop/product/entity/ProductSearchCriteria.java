package com.furnitureshop.product.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductSearchCriteria {
    private String productName;
    private List<Long> categoryId;
    private List<Long> brandId;
}
