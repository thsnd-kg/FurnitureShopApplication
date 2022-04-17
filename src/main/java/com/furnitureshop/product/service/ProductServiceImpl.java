package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.ProductDto;
import com.furnitureshop.product.entity.Brand;
import com.furnitureshop.product.entity.Category;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BrandService brandService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Product createProduct(ProductDto dto) {
        Product product = handleData(dto, false);

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductDto dto) {
        Product product = handleData(dto, true);

        return productRepository.save(product);
    }

    public boolean isExisted(Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        return product.isPresent();
    }

    public Product handleData(ProductDto dto, boolean hasId) {
        Product product = new Product();

        if (hasId) {
            if (dto.getProductId() == null)
                throw new IllegalStateException("Product id must not be null");

            if (isExisted(dto.getProductId()))
                product = productRepository.getById(dto.getProductId());
            else
                throw new IllegalStateException("Product not exists");
        }

        Category category = categoryService.getCategoryById(dto.getCategoryId());
        Brand brand = brandService.getBrandById(dto.getBrandId());

        product.setCategory(category);
        product.setBrand(brand);
        product.setProductName(dto.getProductName());

        return product;
    }
}
