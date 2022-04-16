package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.ProductVariantDto;
import com.furnitureshop.product.entity.ProductVariant;

import java.util.List;

public interface ProductVariantService {
    List<ProductVariant> getProductVariants();

    List<ProductVariant> getProductVariantsByProductId(Long productId);

    List<ProductVariant> getProductVariantsByVariantId(Long variantId);

    ProductVariant getProductVariantById(Long variantId, Long productId);

    ProductVariant createProductVariant(ProductVariantDto dto);

    ProductVariant updateProductVariant(ProductVariantDto dto);
}
