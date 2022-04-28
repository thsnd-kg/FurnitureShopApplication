package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.VariantValue;
import com.furnitureshop.product.entity.VariantValuePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantValueRepository extends JpaRepository<VariantValue, VariantValuePK> {
    @Query("SELECT DISTINCT vv.option.optionId, vv.optionValue, vv.optionImage FROM VariantValue vv WHERE vv.productVariant IN " +
            "(SELECT pv.variantId FROM ProductVariant pv WHERE pv.product.productId = ?1)" +
            "ORDER BY vv.option.optionId")
    VariantValue getOptionValue(Long productId);
}
