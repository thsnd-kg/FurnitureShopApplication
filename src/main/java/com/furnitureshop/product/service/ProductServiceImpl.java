package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.product.CreateProductDto;
import com.furnitureshop.product.dto.product.UpdateProductDto;
import com.furnitureshop.product.entity.Brand;
import com.furnitureshop.product.entity.Category;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.repository.ProductCriteriaRepository;
import com.furnitureshop.product.repository.ProductRepository;
import com.furnitureshop.product.search.ProductPage;
import com.furnitureshop.product.search.ProductSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductCriteriaRepository productCriteriaRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ProductCriteriaRepository productCriteriaRepository, BrandService brandService, CategoryService categoryService) {
        this.repository = repository;
        this.productCriteriaRepository = productCriteriaRepository;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> product = repository.findById(productId);

        if (!product.isPresent())
            throw new IllegalStateException("Product does not exists");

        return product.get();
    }

    @Override
    public Page<Product> getProducts(ProductPage productPage, ProductSearchCriteria productSearchCriteria) {
        return productCriteriaRepository.findAllWithFilters(productPage, productSearchCriteria);
    }

    @Override
    public List<Product> getProducts() {
        return repository.findAll();
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
    public Product updateProduct(UpdateProductDto dto) {
        Product product = getProductById(dto.getProductId());

        if (!Objects.equals(dto.getBrandId(), product.getBrand().getBrandId())) {
            Brand brand = brandService.getBrandById(dto.getBrandId());
            product.setBrand(brand);
        }

        if (!Objects.equals(dto.getCategoryId(), product.getCategory().getCategoryId())) {
            Category category = categoryService.getCategoryById(dto.getCategoryId());
            product.setCategory(category);
        }

        product.setProductName(dto.getProductName());
        product.setProductDesc(dto.getProductDesc());
        product.setImage(dto.getImage());

        return repository.save(product);
    }

    @Override
    public Boolean deleteProduct(Long productId) {
        Product product = getProductById(productId);

        product.setIsDeleted(true);
        product.getVariants().forEach(e -> e.setIsDeleted(true));
        repository.save(product);

        return true;
    }
}
