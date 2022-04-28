package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.VariantValue;
import com.furnitureshop.product.entity.VariantValuePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantValueRepository extends JpaRepository<VariantValue, VariantValuePK> {
    @Query("SELECT DISTINCT v.option.optionId, v.optionValue, v.optionImage FROM VariantValue v WHERE v.productVariant.product.productId = ?1")
    VariantValue getOptionValue(Long productId);
}
