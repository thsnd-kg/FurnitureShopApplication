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
    @Query("SELECT DISTINCT(vv.optionValue) FROM VariantValue vv WHERE vv.productVariant.product.productId = ?1")
    Optional<List<VariantValue>> findByProductId(Long productId);
}
