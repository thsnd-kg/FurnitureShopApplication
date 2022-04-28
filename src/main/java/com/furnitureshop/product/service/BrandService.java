package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.BrandDto;
import com.furnitureshop.product.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getBrands();

    List<Brand> getBrandsActive();

    Brand getBrandById(Long brandId);

    Brand createBrand(BrandDto dto);

    Brand updateBrand(BrandDto dto);

    Boolean deleteBrand(Long brandId);
}
