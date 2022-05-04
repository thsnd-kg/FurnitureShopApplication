package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateVariantDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.entity.Variant;
import com.furnitureshop.product.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantServiceImpl implements VariantService {
    private final VariantRepository repository;
    private final ProductService productService;
    private final ValueService valueService;

    @Autowired
    public VariantServiceImpl(VariantRepository repository, ProductService productService, ValueService valueService) {
        this.repository = repository;
        this.productService = productService;
        this.valueService = valueService;
    }

    @Override
    public List<Variant> getProductVariants() {
        return repository.findAll();
    }

    @Override
    public Variant createProductVariant(CreateVariantDto dto) {
        Variant variant = new Variant();

        Product product = productService.getProductById(dto.getProductId());

        variant.setProduct(product);
        variant.setImage(dto.getImage());
        variant.setSku(dto.getSku());

        Variant result = repository.save(variant);

        dto.getVariantValues().forEach(variantValue -> valueService.createVariantValue(variantValue, result));

        return result;
    }

    @Override
    public List<Object> getOptionValues(Long productId) {
        return valueService.getOptionValues(productId);
    }

    @Override
    public List<Long> findVariantId(Long productId, List<String> optionValue) {
        return valueService.findVariantId(productId, optionValue);
    }
}