package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateVariantDto;
import com.furnitureshop.product.dto.variant.UpdateVariantDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.entity.Variant;
import com.furnitureshop.product.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public Variant getVariantById(Long variantId) {
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
        variant.setPrice(dto.getPrice());

        Variant result = repository.save(variant);

        dto.getValues().forEach(value -> valueService.createValue(value, result));

        return result;
    }

    @Override
    public Variant updateVariant(UpdateVariantDto dto) {
        Variant variant = getVariantById(dto.getVariantId());

        if (!Objects.equals(variant.getProduct().getProductId(), dto.getProductId())) {
            Product product = productService.getProductById(dto.getProductId());
            variant.setProduct(product);
        }

        valueService.updateValue(dto.getValues(), variant);

        variant.setImage(dto.getImage());
        variant.setSku(dto.getSku());
        variant.setPrice(dto.getPrice());

        return repository.save(variant);
    }

    @Override
    public Variant findVariant(Long productId, List<String> optionValue) {
        return valueService.findVariant(productId, optionValue);
    }

    @Override
    public Boolean deleteVariant(Long variantId) {
        Variant variant = getVariantById(variantId);

        valueService.deleteValue(variantId);

        variant.setIsDeleted(true);
        repository.save(variant);

        return true;
    }
}