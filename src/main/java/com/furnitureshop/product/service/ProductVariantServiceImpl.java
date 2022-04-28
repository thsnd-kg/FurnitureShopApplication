package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateProductVariantDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.entity.VariantValue;
import com.furnitureshop.product.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository repository;
    private final ProductService productService;
    private final VariantValueService variantValueService;

    @Autowired
    public ProductVariantServiceImpl(ProductVariantRepository repository, ProductService productService, VariantValueService variantValueService) {
        this.repository = repository;
        this.productService = productService;
        this.variantValueService = variantValueService;
    }

    @Override
    public List<ProductVariant> getProductVariants() {
        return repository.findAll();
    }

    @Override
    public ProductVariant createProductVariant(CreateProductVariantDto dto) {
        ProductVariant productVariant = new ProductVariant();

        Product product = productService.getProductById(dto.getProductId());

        productVariant.setProduct(product);
        productVariant.setImage(dto.getImage());
        productVariant.setSku(dto.getSku());

        ProductVariant result = repository.save(productVariant);

        dto.getVariantValues().forEach(variantValue -> variantValueService.createVariantValue(variantValue, result));

        return result;
    }

    @Override
    public List<Object> getOptionValues(Long productId) {
        return variantValueService.findByProductId(productId);
    }
}