package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.ProductVariantDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.entity.ProductVariantPK;
import com.furnitureshop.product.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductService productService;

    @Autowired
    public ProductVariantServiceImpl(ProductVariantRepository productVariantRepository, ProductService productService) {
        this.productVariantRepository = productVariantRepository;
        this.productService = productService;
    }

    @Override
    public List<ProductVariant> getProductVariants() {
        return productVariantRepository.findAll();
    }

    @Override
    public List<ProductVariant> getProductVariantsByProductId(Long productId) {
        return productVariantRepository.findByProductId(productId);
    }

    @Override
    public List<ProductVariant> getProductVariantsByVariantId(Long variantId) {
        return productVariantRepository.findByVariantId(variantId);
    }

    @Override
    public ProductVariant getProductVariantById(Long variantId, Long productId) {
        return productVariantRepository.findById(variantId, productId).orElse(null);
    }

    @Override
    public ProductVariant createProductVariant(ProductVariantDto dto) {
        ProductVariant productVariant = handleData(dto, false);

        return productVariantRepository.save(productVariant);
    }

    @Override
    public ProductVariant updateProductVariant(ProductVariantDto dto) {
        ProductVariant productVariant = handleData(dto, true);

        return productVariantRepository.save(productVariant);
    }

    public boolean isExisted(Long productId, Long variantId) {
        Optional<ProductVariant> productVariant = productVariantRepository.findById(new ProductVariantPK(productId, variantId));

        return productVariant.isPresent();
    }

    public ProductVariant handleData(ProductVariantDto dto, boolean hasId) {
        ProductVariant productVariant = new ProductVariant();

        if (hasId) {
            if (dto.getProductId() == null)
                throw new IllegalStateException("Product id must not be null");
            if (dto.getVariantId() == null)
                throw new IllegalStateException("Variant id must not be null");

            if (isExisted(dto.getProductId(), dto.getVariantId()))
                productVariant = productVariantRepository.getById(new ProductVariantPK(dto.getProductId(), dto.getVariantId()));
            else
                throw new IllegalStateException("Product variant not exists");
        } else {
            List<ProductVariant> productVariants = productVariantRepository.findByProductId(dto.getProductId());
            ProductVariant max = productVariants.stream().max(Comparator.comparing(ProductVariant::getVariantId)).orElse(null);
            long id = max == null ? 0 : max.getVariantId();
            productVariant.setVariantId(id + 1);
        }

        Product product = productService.getProduct(dto.getProductId());

        productVariant.setProductId(dto.getProductId());
        productVariant.setProduct(product);
        productVariant.setPrice(dto.getPrice());
        productVariant.setImage(dto.getImage());

        return productVariant;
    }
}
