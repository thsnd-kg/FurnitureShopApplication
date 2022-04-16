package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.entity.ProductVariantPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, ProductVariantPK> {
    @Query("SELECT pv FROM ProductVariant pv WHERE pv.productId = ?1")
    Optional<ProductVariant> findByProductId(Long productId);

    @Query("SELECT pv FROM ProductVariant pv WHERE pv.variantId = ?1")
    Optional<ProductVariant> findByVariantId(Long variantId);

    @Query("SELECT pv FROM ProductVariant pv WHERE pv.variantId = ?1 and pv.productId = ?2")
    Optional<ProductVariant> findById(Long variantId, Long productId);
}
