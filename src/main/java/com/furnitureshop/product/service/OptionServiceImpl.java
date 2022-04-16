package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.OptionDto;
import com.furnitureshop.product.entity.Category;
import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.repository.CategoryRepository;
import com.furnitureshop.product.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    public List<Option> getOptionsByOptionId(Long optionId) {
        return optionRepository.findByOptionId(optionId);
    }

    @Override
    public List<Option> getOptionsByCategoryId(Long categoryId) {
        return optionRepository.findByCategoryId(categoryId);
    }

    @Override
    public Option getOptionById(Long optionId, Long categoryId) {
        return optionRepository.findById(optionId, categoryId).orElse(null);
    }

    @Override
    public Option createOption(OptionDto dto) {
        Optional<Category> categoryOptional = categoryRepository.findById(dto.getCategoryId());
        List<Option> options = optionRepository.findByCategoryId(dto.getCategoryId());

        if (!categoryOptional.isPresent()) {
            throw new IllegalStateException("Category not exists");
        }

        Option max = options.stream().max(Comparator.comparing(Option::getOptionId)).orElse(null);
        Long id = max == null ? 0 : max.getOptionId();

        Option option = new Option();
        option.setOptionId(id + 1);
        option.setOptionName(dto.getOptionName());
        option.setCategoryId(dto.getCategoryId());

        return optionRepository.save(option);
    }

    @Override
    public Option updateOption(OptionDto dto) {
        Optional<Category> categoryOptional = categoryRepository.findById(dto.getCategoryId());
        Optional<Option> optionOptional = optionRepository.findById(dto.getOptionId(), dto.getCategoryId());

        if (!optionOptional.isPresent()) {
            throw new IllegalStateException("Option not exists");
        } else if (!categoryOptional.isPresent()) {
            throw new IllegalStateException("Category not exists");
        }

        Option option = optionOptional.get();
        option.setCategoryId(dto.getCategoryId());
        option.setOptionName(dto.getOptionName());

        return optionRepository.save(option);
    }
}
