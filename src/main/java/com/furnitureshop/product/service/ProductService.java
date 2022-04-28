package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.product.CreateProductDto;
import com.furnitureshop.product.dto.product.UpdateProductDto;
import com.furnitureshop.product.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product getProductById(Long productId);

    Page<Product> getProductsWithPagination(int offset, int pageSize);

    Page<Product> getProductsWithPaginationAndSorting(int offset, int pageSize, String field);

    Product createProduct(CreateProductDto dto);

    Product updateProduct(UpdateProductDto dto);
}
