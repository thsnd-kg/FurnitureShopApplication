package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.product.CreateProductDto;
import com.furnitureshop.product.dto.product.UpdateProductDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.search.ProductPage;
import com.furnitureshop.product.search.ProductSearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getProductById(Long productId);

    Page<Product> getProducts(ProductPage productPage, ProductSearchCriteria productSearchCriteria);

    List<Product> getProducts();

    List<Product> getProductsActive();

    Product createProduct(CreateProductDto dto);

    Product updateProduct(UpdateProductDto dto);

    Boolean deleteProduct(Long productId);
}
