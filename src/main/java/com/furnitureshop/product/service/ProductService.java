package com.furnitureshop.product.service;

import com.furnitureshop.product.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductById(Long id);
    void addProduct(Product product);
}
