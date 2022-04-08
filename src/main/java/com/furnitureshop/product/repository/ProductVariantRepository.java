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
    Optional<ProductVariant> getProductVariantByProductId(Long productId);
    @Query("SELECT pv FROM ProductVariant pv WHERE pv.variantId = ?1")
    Optional<ProductVariant> getProductVariantByVariantId(Long variantId);
    @Query("SELECT pv FROM ProductVariant pv WHERE pv.productId = ?1 and pv.variantId = ?2")
    Optional<ProductVariant> getProductVariant(Long productId, Long variantId);
}
