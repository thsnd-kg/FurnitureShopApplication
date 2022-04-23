package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.ProductVariantDto;
import com.furnitureshop.product.dto.variant.CreateProductVariantDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductService productService;
    private final VariantValueService variantValueService;

    @Autowired
    public ProductVariantServiceImpl(ProductVariantRepository productVariantRepository, ProductService productService, VariantValueService variantValueService) {
        this.productVariantRepository = productVariantRepository;
        this.productService = productService;
        this.variantValueService = variantValueService;
    }

    @Override
    public List<ProductVariant> getProductVariants() {
        return productVariantRepository.findAll();
    }

    @Override
    public ProductVariant createProductVariant(CreateProductVariantDto dto) {
        ProductVariant productVariant = new ProductVariant();

        Product product = productService.getProduct(dto.getProductId());

        productVariant.setProduct(product);
        productVariant.setImage(dto.getImage());
        productVariant.setSku(dto.getSku());

        ProductVariant result = productVariantRepository.save(productVariant);

        dto.getVariantValues().forEach(variantValue -> {variantValueService.createVariantValue(variantValue, result);});

        return result;
    }

    public ProductVariant updateProductVariant(ProductVariantDto dto) {
        return null;
    }
}
