package com.furnitureshop.product.dto.category;

import com.furnitureshop.product.entity.Option;
import lombok.Getter;

@Getter
public class GetOptionDto {
    private final Long optionId;
    private final String optionName;

    public GetOptionDto(Option option) {
        this.optionId = option.getOptionId();
        this.optionName = option.getOptionName();
    }
}
