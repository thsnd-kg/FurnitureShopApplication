package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.product.CreateProductDto;
import com.furnitureshop.product.dto.product.UpdateProductDto;
import com.furnitureshop.product.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getProductById(Long productId);

    List<Product> findByProductName(String name, int offset);

    List<Product> getProducts(int offset);

    List<Product> getProductsActive(int offset);

    Product createProduct(CreateProductDto dto);

    Product updateProduct(UpdateProductDto dto);

    Boolean deleteProduct(Long productId);
}
