package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.ProductDto;
import com.furnitureshop.product.dto.product.CreateProductDto;
import com.furnitureshop.product.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProductById(Long productId);

    Product createProduct(CreateProductDto dto);

    Product updateProduct(ProductDto dto);
}
