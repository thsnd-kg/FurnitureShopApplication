package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.OptionDto;
import com.furnitureshop.product.entity.Option;

import java.util.List;

public interface OptionService {
    List<Option> getOptions();

    List<Option> getOptionsByCategoryId(Long categoryId);

    Option getOptionById(Long optionId, Long categoryId);

    Option createOption(OptionDto dto);

    Option updateOption(OptionDto dto);
}
