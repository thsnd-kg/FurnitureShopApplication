package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.ProductDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Product createProduct(ProductDto dto) {
        Product product = new Product();
        product.setBrandId(dto.getBrandId());
        product.setCategoryId(dto.getCategoryId());
        product.setProductName(dto.getProductName());

        return repository.save(product);
    }

    @Override
    public Product updateProduct(ProductDto dto) {
        Product brand = repository.getById(dto.getProductId());

        if(dto.getProductName() != null)
            brand.setProductName(dto.getProductName());

        return repository.save(brand);
    }
}
