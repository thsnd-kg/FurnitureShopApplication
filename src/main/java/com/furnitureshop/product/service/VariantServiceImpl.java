package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateVariantDto;
import com.furnitureshop.product.dto.variant.GetValueDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.entity.Variant;
import com.furnitureshop.product.repository.VariantRepository;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Variant> getVariants() {
        return repository.findAll();
    }

    @Override
    public Variant getVariant(Long variantId) {
        Optional<Variant> variant = repository.findById(variantId);

        if (!variant.isPresent()) {
            throw new IllegalStateException("Variant not exists");
        }

        return variant.get();
    }

    @Override
    public Variant createVariant(CreateVariantDto dto) {
        Variant variant = new Variant();

        Product product = productService.getProductById(dto.getProductId());

        variant.setProduct(product);
        variant.setImage(dto.getImage());
        variant.setSku(dto.getSku());

        Variant result = repository.save(variant);

        dto.getVariantValues().forEach(variantValue -> valueService.createValue(variantValue, result));

        return result;
    }

    @Override
    public Variant findVariant(Long productId, List<String> optionValue) {
        return valueService.findVariant(productId, optionValue);
    }
}