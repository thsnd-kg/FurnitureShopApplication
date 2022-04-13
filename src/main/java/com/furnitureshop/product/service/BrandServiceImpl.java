package com.furnitureshop.product.service;
import com.furnitureshop.product.dto.BrandDto;
import com.furnitureshop.product.entity.Brand;
import com.furnitureshop.product.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService{
    @Autowired
    private BrandRepository repo;

    @Override
    public List<Brand> getBrands() {
        return repo.findAll();
    }

    @Override
    public List<Brand> getBrandsActive() {
        return repo.findByIsDeletedFalse();
    }

    @Override
    public Brand getBrandById(Long brandId) {


        return repo.getById(brandId);
    }

    @Override
    public Brand createBrand(BrandDto dto) {
        Brand brand = new Brand();
        brand.setBrandName(dto.getBrandName());
        brand.setDescription(dto.getDescription());
        brand.setIsDeleted(false);
        return repo.save(brand);
    }

    @Override
    public Brand updateBrand(BrandDto dto) {
        Brand brand = repo.getById(dto.getBrandId());

        if(dto.getBrandName() != null)
            brand.setBrandName(dto.getBrandName());

        if(dto.getDescription() != null)
            brand.setDescription(dto.getDescription());

        return repo.save(brand);
    }

    @Override
    public Boolean deleteBrand(Long brandId) {
        Brand brand = repo.getById(brandId);
        brand.setIsDeleted(true);
        repo.save(brand);
        return true;
    }
}
