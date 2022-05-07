package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.product.CreateProductDto;
import com.furnitureshop.product.dto.product.UpdateProductDto;
import com.furnitureshop.product.dto.variant.GetValueDto;
import com.furnitureshop.product.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getProductById(Long productId);

    Page<Product> getProductsWithPagination(int offset, int pageSize);

    Page<Product> findByProductName(String name, int offset);

    List<Product> getProducts();

    Product createProduct(CreateProductDto dto);

    Product updateProduct(UpdateProductDto dto);

    Boolean deleteProduct(Long productId);
}
