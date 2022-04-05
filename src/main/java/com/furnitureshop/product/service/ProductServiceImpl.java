package com.furnitureshop.product.service;

import com.furnitureshop.product.entity.OptionValue;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void addProduct(Product product) {

    }
}
