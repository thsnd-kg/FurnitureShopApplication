package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.ProductVariantDto;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.repository.ProductRepository;
import com.furnitureshop.product.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductVariantServiceImpl(ProductVariantRepository productVariantRepository, ProductRepository productRepository) {
        this.productVariantRepository = productVariantRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductVariant> getProductVariants() {
        return productVariantRepository.findAll();
    }

    @Override
    public ProductVariant getProductVariantByProductId(Long productId) {
        return productVariantRepository.findByProductId(productId).orElse(null);
    }

    @Override
    public ProductVariant getProductVariantByVariantId(Long variantId) {
        return productVariantRepository.findByVariantId(variantId).orElse(null);
    }

    @Override
    public ProductVariant getProductVariantById(Long variantId, Long productId) {
        return productVariantRepository.findById(variantId, productId).orElse(null);
    }

    @Override
    public ProductVariant createProductVariant(ProductVariantDto dto) {
        var productOptional = productRepository.findById(dto.getProductId());
        var productVariantOptional = productVariantRepository.findByProductId(dto.getProductId());

        if (productOptional.isEmpty()) {
            throw new IllegalStateException("Product not exists");
        }

        ProductVariant max = productVariantOptional.stream().max(Comparator.comparing(ProductVariant::getVariantId)).orElse(null);
        Long id = max == null ? 0 : max.getVariantId();

        ProductVariant productVariant = new ProductVariant();
        productVariant.setVariantId(id + 1);
        productVariant.setProductId(dto.getProductId());
        productVariant.setImage(dto.getImage());
        productVariant.setPrice(dto.getPrice());

        return productVariantRepository.save(productVariant);
    }

    @Override
    public ProductVariant updateProductVariant(ProductVariantDto dto) {
        var productVariantOptional = productVariantRepository.findById(dto.getVariantId(), dto.getProductId());
        var productOptional = productRepository.findById(dto.getProductId());

        if (productVariantOptional.isEmpty()) {
            throw new IllegalStateException("Product variant not exists");
        } else if (productOptional.isEmpty()) {
            throw new IllegalStateException("Product not exists");
        }

        ProductVariant productVariant = productVariantOptional.get();
        productVariant.setProductId(dto.getProductId());
        productVariant.setPrice(dto.getPrice());
        productVariant.setImage(dto.getImage());

        return productVariantRepository.save(productVariant);
    }
}
