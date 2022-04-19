package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.VariantValue;
import com.furnitureshop.product.entity.VariantValuePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VariantValueRepository extends JpaRepository<VariantValue, VariantValuePK> {
    @Query("SELECT v FROM VariantValue v WHERE v.productId = ?1 AND v.variantId = ?2 AND v.optionId = ?3")
    Optional<VariantValue> findById(Long productId, Long variantId, Long optionId);

    @Query("SELECT v FROM VariantValue v WHERE v.variantId = ?1")
    List<VariantValue> findByVariantId(Long variantId);

    @Query("SELECT v FROM VariantValue v WHERE v.productId = ?1")
    List<VariantValue> findByProductId(Long productId);

    @Query("SELECT v FROM VariantValue v WHERE v.optionId = ?1")
    List<VariantValue> findByOptionId(Long optionId);

    @Query("SELECT DISTINCT v.optionValue FROM VariantValue v WHERE v.productId = ?1 AND v.optionId = ?2")
    List<String> getOptionValue(Long productId, Long optionId);
}
