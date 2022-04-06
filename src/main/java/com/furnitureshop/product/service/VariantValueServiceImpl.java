package com.furnitureshop.product.service;

import com.furnitureshop.product.entity.VariantValue;
import com.furnitureshop.product.repository.VariantValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantValueServiceImpl implements VariantValueService {
    private final VariantValueRepository variantValueRepository;

    @Autowired
    public VariantValueServiceImpl(VariantValueRepository variantValueRepository) {
        this.variantValueRepository = variantValueRepository;
    }

    @Override
    public List<VariantValue> getVariantValues() {
        return variantValueRepository.findAll();
    }
}
