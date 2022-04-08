package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.BrandDto;
import com.furnitureshop.product.dto.ProductDto;
import com.furnitureshop.product.entity.Brand;
import com.furnitureshop.product.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductById(Long id);
    Product createProduct(ProductDto dto);
    Product updateProduct(ProductDto dto);
}
