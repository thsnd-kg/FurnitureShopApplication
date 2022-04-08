package com.furnitureshop.product.service;

import com.furnitureshop.product.entity.ProductVariant;

import java.util.List;

public interface ProductVariantService {
    List<ProductVariant> getProductVariants();
    ProductVariant getProductVariantByProductId(Long productId);
    ProductVariant getProductVariantByVariantId(Long variantId);
    ProductVariant getProductVariant(Long productId, Long variantId);
}
