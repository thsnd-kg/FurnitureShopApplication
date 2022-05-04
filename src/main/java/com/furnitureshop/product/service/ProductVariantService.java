package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateProductVariantDto;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.entity.VariantValue;

import java.util.List;

public interface ProductVariantService {
    List<ProductVariant> getProductVariants();

    ProductVariant createProductVariant(CreateProductVariantDto dto);

    List<Object> getOptionValues(Long productId);

    List<Long> findVariantId(Long productId, List<String> optionValue);
}
