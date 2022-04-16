package com.furnitureshop.product.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BrandDto {
    @NotNull(message = "Brand id must not be null")
    private Long brandId;

    @NotBlank(message = "Brand name must be not blank")
    @Size(max = 50, message = "Brand name must be less than 50 characters")
    private String brandName;

    @Size(max = 100, message = "Brand description must be less than 100 characters")
    private String description;
}
