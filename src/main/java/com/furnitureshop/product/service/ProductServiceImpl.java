package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.ProductDto;
import com.furnitureshop.product.dto.product.CreateProductDto;
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
    private final ProductRepository repository;
    private final BrandService brandService;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, BrandService brandService, CategoryService categoryService) {
        this.repository = repository;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> product = repository.findById(productId);

        if (!product.isPresent())
            throw new IllegalStateException("Product does not exists");

        return product.get();
    }

    @Override
    public Product createProduct(CreateProductDto dto) {
        Product product = new Product();

        Brand brand = brandService.getBrandById(dto.getBrandId());
        Category category = categoryService.getCategoryById(dto.getCategoryId());

        product.setProductName(dto.getProductName());
        product.setProductDesc(dto.getProductDesc());
        product.setImage(dto.getImage());
        product.setBrand(brand);
        product.setCategory(category);

        return repository.save(product);
    }

    @Override
    public Product updateProduct(ProductDto dto) {
        return null;
    }
}
