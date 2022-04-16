package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.OptionDto;
import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.repository.CategoryRepository;
import com.furnitureshop.product.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public OptionServiceImpl(OptionRepository optionRepository, CategoryRepository categoryRepository) {
        this.optionRepository = optionRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Option> getOptions() {
        return optionRepository.findAll();
    }

    @Override
    public Option getOptionByOptionId(Long optionId) {
        return optionRepository.findByOptionId(optionId).orElse(null);
    }

    @Override
    public Option getOptionByCategoryId(Long categoryId) {
        return optionRepository.findByCategoryId(categoryId).orElse(null);
    }

    @Override
    public Option getOptionById(Long optionId, Long categoryId) {
        return optionRepository.findById(optionId, categoryId).orElse(null);
    }

    @Override
    public Option createOption(OptionDto dto) {
        var categoryOptional = categoryRepository.findById(dto.getCategoryId());
        var optionOptional = optionRepository.findByCategoryId(dto.getCategoryId());

        if (categoryOptional.isEmpty()) {
            throw new IllegalStateException("Category not exists");
        }

        Option max = optionOptional.stream().max(Comparator.comparing(Option::getOptionId)).orElse(null);
        Long id = max == null ? 0 : max.getOptionId();

        Option option = new Option();
        option.setOptionId(id + 1);
        option.setOptionName(dto.getOptionName());
        option.setCategoryId(dto.getCategoryId());

        return optionRepository.save(option);
    }

    @Override
    public Option updateOption(OptionDto dto) {
        var categoryOptional = categoryRepository.findById(dto.getCategoryId());
        var optionOptional = optionRepository.findById(dto.getOptionId(), dto.getCategoryId());

        if (optionOptional.isEmpty()) {
            throw new IllegalStateException("Option not exists");
        } else if (categoryOptional.isEmpty()) {
            throw new IllegalStateException("Category not exists");
        }

        Option option = optionOptional.get();
        option.setCategoryId(dto.getCategoryId());
        option.setOptionName(dto.getOptionName());

        return optionRepository.save(option);
    }
}
