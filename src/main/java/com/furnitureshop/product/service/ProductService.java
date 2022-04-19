package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.ProductDto;
import com.furnitureshop.product.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long productId);

    Product createProduct(ProductDto dto);

    Product updateProduct(ProductDto dto);
}
