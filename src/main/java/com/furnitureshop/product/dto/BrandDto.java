package com.furnitureshop.product.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class BrandDto {
    private Long brandId;

    @NotEmpty
    @NotBlank
    private String brandName;
    private String description;
}
