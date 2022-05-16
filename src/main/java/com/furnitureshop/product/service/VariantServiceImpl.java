package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateValueDto;
import com.furnitureshop.product.dto.variant.CreateVariantDto;
import com.furnitureshop.product.dto.variant.UpdateValueDto;
import com.furnitureshop.product.dto.variant.UpdateVariantDto;
import com.furnitureshop.product.entity.*;
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
    private final OptionService optionService;

    @Autowired
    public VariantServiceImpl(VariantRepository repository, ProductService productService, ValueService valueService, OptionService optionService) {
        this.repository = repository;
        this.productService = productService;
        this.valueService = valueService;
        this.optionService = optionService;
    }

    @Override
    public List<Variant> getVariantsActiveByProductId(Long productId) {
        return repository.findActiveByProductId(productId);
    }

    @Override
    public List<Variant> getVariantsByProductId(Long productId) {
        return repository.findByProductId(productId);
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
    public Variant createVariant(CreateVariantDto createVariant) {
        Variant variant = new Variant();

        Product product = productService.getProductById(createVariant.getProductId());

        variant.setProduct(product);
        variant.setImage(createVariant.getImage());
        variant.setSku(createVariant.getSku());
        variant.setPrice(createVariant.getPrice());

        for (CreateValueDto createValue : createVariant.getOptions()) {
            Value value = new Value();

            Option option = optionService.getOptionById(createValue.getOptionId());

            value.setOptionValue(createValue.getOptionValue());
            value.setOptionImage(createValue.getOptionImage());
            value.setVariant(variant);
            value.setOption(option);

            variant.getValues().add(value);
        }

        return repository.save(variant);
    }

    @Override
    public Variant updateVariant(UpdateVariantDto updateVariant) {
        Variant variant = getVariantById(updateVariant.getVariantId());
        variant.getValues().clear();

        if (!Objects.equals(variant.getProduct().getProductId(), updateVariant.getProductId())) {
            Product product = productService.getProductById(updateVariant.getProductId());
            variant.setProduct(product);
        }

        for (UpdateValueDto dto : updateVariant.getOptions()) {
            Value value = valueService.getValueById(variant.getVariantId(), dto.getOptionId());

            value.setOptionValue(dto.getOptionValue());
            value.setOptionImage(dto.getOptionImage());

            variant.getValues().add(value);
        }

        variant.setImage(updateVariant.getImage());
        variant.setSku(updateVariant.getSku());
        variant.setPrice(updateVariant.getPrice());

        return repository.save(variant);
    }

    @Override
    public Variant findVariant(Long productId, List<String> optionValue) {
        return valueService.findVariant(productId, optionValue);
    }

    @Override
    public Boolean deleteVariant(Long variantId) {
        Variant variant = getVariantById(variantId);

        variant.setIsDeleted(true);
        repository.save(variant);

        return true;
    }
}