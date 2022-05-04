package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.Value;
import com.furnitureshop.product.entity.ValuePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ValueRepository extends JpaRepository<Value, ValuePK> {
    @Query("SELECT DISTINCT v.option.optionId, v.optionValue, v.optionImage FROM Value v WHERE v.variant.product.productId = ?1")
    Optional<List<Object>> getOptionValues(Long productId);

    @Query("SELECT v.variant.variantId FROM Value v WHERE v.variant.product.productId = ?1 AND v.optionValue = ?2")
    Optional<List<Long>> findVariantId(Long productId, String optionValue);
}
