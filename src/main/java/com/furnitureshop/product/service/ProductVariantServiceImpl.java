package com.furnitureshop.product.service;

import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository repository;

    @Autowired
    public ProductVariantServiceImpl(ProductVariantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductVariant> getProductVariants() {
        return repository.findAll();
    }

    @Override
    public ProductVariant getProductVariantByProductId(Long productId) {
        return repository.getProductVariantByProductId(productId).orElse(null);
    }

    @Override
    public ProductVariant getProductVariantByVariantId(Long variantId) {
        return repository.getProductVariantByVariantId(variantId).orElse(null);
    }

    @Override
    public ProductVariant getProductVariant(Long productId, Long variantId) {
        return repository.getProductVariant(productId, variantId).orElse(null);
    }
}
