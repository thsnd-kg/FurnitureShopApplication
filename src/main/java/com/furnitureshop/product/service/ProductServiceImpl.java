package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.ProductDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public Product createProduct(ProductDto dto) {
        Product product = new Product();
        product.setBrandId(dto.getBrandId());
        product.setCategoryId(dto.getCategoryId());
        product.setProductName(dto.getProductName());

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductDto dto) {
        Product brand = productRepository.getById(dto.getProductId());

        if(dto.getProductName() != null)
            brand.setProductName(dto.getProductName());

        return productRepository.save(brand);
    }

    @Override
    public Boolean deteleProduct(Long productId) {
        return null;
    }
}
