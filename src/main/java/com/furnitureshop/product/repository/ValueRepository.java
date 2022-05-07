package com.furnitureshop.product.repository;

import com.furnitureshop.product.dto.variant.GetValueDto;
import com.furnitureshop.product.entity.Value;
import com.furnitureshop.product.entity.ValuePK;
import com.furnitureshop.product.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ValueRepository extends JpaRepository<Value, ValuePK> {
    @Query("SELECT DISTINCT new com.furnitureshop.product.dto.variant.GetValueDto(v.option.optionId, v.option.optionName, v.optionValue, v.optionImage) " +
            "FROM Value v " +
            "WHERE v.variant.product.productId = ?1 AND v.variant.isDeleted = FALSE " +
            "ORDER BY v.option.optionId")
    Optional<List<GetValueDto>> getOptionValues(Long productId);

    @Query("SELECT v.variant " +
            "FROM Value v " +
            "WHERE v.variant.product.productId = ?1 AND v.optionValue = ?2 AND v.variant.isDeleted = FALSE")
    Optional<List<Variant>> findVariantId(Long productId, String optionValue);

    Optional<List<Value>> findById_VariantId(Long variantId);
}
