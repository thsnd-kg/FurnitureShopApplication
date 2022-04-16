package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.ProductDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.repository.BrandRepository;
import com.furnitureshop.product.repository.CategoryRepository;
import com.furnitureshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Product createProduct(ProductDto dto) {
        var brandOptional = brandRepository.findById(dto.getBrandId());
        var categoryOptional = categoryRepository.findById(dto.getCategoryId());

        if (brandOptional.isEmpty()) {
            throw new IllegalStateException("Brand not exists");
        } else if (categoryOptional.isEmpty()) {
            throw new IllegalStateException("Category not exists");
        } else if (productRepository.findByName(dto.getProductName()).isPresent()) {
            throw new IllegalStateException("Product name already exists");
        }

        Product product = new Product();
        product.setBrand(brandOptional.get());
        product.setCategory(categoryOptional.get());
        product.setProductName(dto.getProductName());

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductDto dto) {
        var brandOptional = brandRepository.findById(dto.getBrandId());
        var categoryOptional = categoryRepository.findById(dto.getCategoryId());
        var productOptional = productRepository.findById(dto.getProductId());

        if (productOptional.isEmpty()) {
            throw new IllegalStateException("Product not exists");
        } else if (brandOptional.isEmpty()) {
            throw new IllegalStateException("Brand not exists");
        } else if (categoryOptional.isEmpty()) {
            throw new IllegalStateException("Category not exists");
        }

        Product product = productOptional.get();
        product.setProductName(dto.getProductName());
        product.setBrand(brandOptional.get());
        product.setCategory(categoryOptional.get());

        return productRepository.save(product);
    }
}
