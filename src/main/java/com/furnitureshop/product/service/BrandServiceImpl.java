package com.furnitureshop.product.service;
import com.furnitureshop.product.dto.BrandDto;
import com.furnitureshop.product.entity.Brand;
import com.furnitureshop.product.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository repository;

    @Autowired
    public BrandServiceImpl(BrandRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Brand> getBrands() {
        return repository.findAll();
    }

    @Override
    public List<Brand> getBrandsActive() {
        return repository.findByIsDeletedFalse();
    }

    @Override
    public Brand getBrandById(Long brandId) {
        return repository.getById(brandId);
    }

    @Override
    public Brand createBrand(BrandDto dto) {
        Brand brand = new Brand();
        brand.setBrandName(dto.getBrandName());
        brand.setBrandDescription(dto.getDescription());
        brand.setIsDeleted(false);
        return repository.save(brand);
    }

    @Override
    public Brand updateBrand(BrandDto dto) {
        Brand brand = repository.getById(dto.getBrandId());

        if(dto.getBrandName() != null)
            brand.setBrandName(dto.getBrandName());

        if(dto.getDescription() != null)
            brand.setBrandDescription(dto.getDescription());

        return repository.save(brand);
    }

    @Override
    public Boolean deleteBrand(Long brandId) {
        Brand brand = repository.getById(brandId);
        brand.setIsDeleted(true);
        repository.save(brand);
        return true;
    }
}
