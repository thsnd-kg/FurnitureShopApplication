package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.OptionValue;
import com.furnitureshop.product.entity.OptionValuePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionValueRepository extends JpaRepository<OptionValue, OptionValuePK> {
    @Query("SELECT ov FROM OptionValue ov WHERE ov.optionId = ?1")
    Optional<OptionValue> getOptionValueByOptionId(Long optionId);

    @Query("SELECT ov FROM OptionValue ov WHERE ov.productId = ?1")
    Optional<OptionValue> getOptionValueByProductId(Long productId);

    @Query("SELECT ov FROM OptionValue ov WHERE ov.valueId = ?1")
    Optional<OptionValue> getOptionValueByValueId(Long valueId);
}
