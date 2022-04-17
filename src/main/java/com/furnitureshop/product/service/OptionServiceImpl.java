package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.OptionDto;
import com.furnitureshop.product.entity.Category;
import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.entity.OptionPK;
import com.furnitureshop.product.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;
    private final CategoryService categoryService;

    @Autowired
    public OptionServiceImpl(OptionRepository optionRepository, CategoryService categoryService) {
        this.optionRepository = optionRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Option> getOptions() {
        return optionRepository.findAll();
    }

    @Override
    public List<Option> getOptionsByCategoryId(Long categoryId) {
        return optionRepository.findByCategoryId(categoryId);
    }

    @Override
    public Option getOptionById(Long optionId, Long categoryId) {
        return optionRepository.findById(new OptionPK(optionId, categoryId)).orElse(null);
    }

    @Override
    public Option createOption(OptionDto dto) {
        Option option = handleData(dto, false);
        return optionRepository.save(option);
    }

    @Override
    public Option updateOption(OptionDto dto) {
        Option option = handleData(dto, true);
        return optionRepository.save(option);
    }

    public boolean isExisted(Long optionId, Long categoryId) {
        Optional<Option> option = optionRepository.findById(new OptionPK(optionId, categoryId));

        return option.isPresent();
    }

    public Option handleData(OptionDto dto, boolean hasId) {
        Option option = new Option();

        if (hasId) {
            if (dto.getOptionId() == null)
                throw new IllegalStateException("Option id must not be null");

            if (isExisted(dto.getOptionId(), dto.getCategoryId()))
                option = optionRepository.getById(new OptionPK(dto.getOptionId(), dto.getCategoryId()));
            else
                throw new IllegalStateException("Option not exists");
        } else {
            List<Option> options = optionRepository.findByCategoryId(dto.getCategoryId());
            Option max = options.stream().max(Comparator.comparing(Option::getOptionId)).orElse(null);
            long id = max == null ? 0 : max.getOptionId();
            option.setOptionId(id + 1);
        }

        Category category = categoryService.getCategoryById(dto.getCategoryId());

        option.setCategoryId(dto.getCategoryId());
        option.setCategory(category);

        if (dto.getOptionName() != null)
            option.setOptionName(dto.getOptionName());

        return option;
    }
}
