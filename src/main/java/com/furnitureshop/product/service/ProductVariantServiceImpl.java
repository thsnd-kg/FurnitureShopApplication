package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.ProductVariantDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.entity.ProductVariantPK;
import com.furnitureshop.product.repository.ProductRepository;
import com.furnitureshop.product.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
    public List<ProductVariant> getProductVariantsByProductId(Long productId) {
        return productVariantRepository.findByProductId(productId);
    }

    @Override
    public List<ProductVariant> getProductVariantsByVariantId(Long variantId) {
        return productVariantRepository.findByVariantId(variantId);
    }

    @Override
    public ProductVariant getProductVariantById(Long variantId, Long productId) {
        return productVariantRepository.findById(variantId, productId).orElse(null);
    }

    @Override
    public ProductVariant createProductVariant(ProductVariantDto dto) {
        Optional<Product> productOptional = productRepository.findById(dto.getProductId());
        List<ProductVariant> productVariants = productVariantRepository.findByProductId(dto.getProductId());

        if (!productOptional.isPresent()) {
            throw new IllegalStateException("Product not exists");
        }

        ProductVariant max = productVariants.stream().max(Comparator.comparing(ProductVariant::getVariantId)).orElse(null);
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
        Optional<ProductVariant> productVariantOptional = productVariantRepository.findById(dto.getVariantId(), dto.getProductId());
        Optional<Product> productOptional = productRepository.findById(dto.getProductId());

        if (!productVariantOptional.isPresent()) {
            throw new IllegalStateException("Product variant not exists");
        } else if (!productOptional.isPresent()) {
            throw new IllegalStateException("Product not exists");
        }

        ProductVariant productVariant = productVariantOptional.get();
        productVariant.setProductId(dto.getProductId());
        productVariant.setPrice(dto.getPrice());
        productVariant.setImage(dto.getImage());

        return productVariantRepository.save(productVariant);
    }
}
