package com.furnitureshop.product.service;

import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {
    private ProductVariantRepository productVariantRepository;

    @Autowired
    public ProductVariantServiceImpl(ProductVariantRepository productVariantRepository) {
        this.productVariantRepository = productVariantRepository;
    }

    public List<ProductVariant> getProductVariants() {
        return productVariantRepository.findAll();
    }
}
