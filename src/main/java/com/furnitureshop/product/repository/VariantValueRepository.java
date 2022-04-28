package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.VariantValue;
import com.furnitureshop.product.entity.VariantValuePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantValueRepository extends JpaRepository<VariantValue, VariantValuePK> {
    @Query("SELECT v.option.optionId, v.optionValue, v.optionImage FROM VariantValue v WHERE v.optionValue IN (SELECT DISTINCT a.optionValue FROM VariantValue a WHERE a.productVariant.product.productId = ?1)")
    VariantValue getOptionValue(Long productId);
}
