package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.entity.ProductVariantPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, ProductVariantPK> {
}
