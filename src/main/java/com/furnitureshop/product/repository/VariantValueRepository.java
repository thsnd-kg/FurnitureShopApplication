package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.VariantValue;
import com.furnitureshop.product.entity.VariantValueKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantValueRepository extends JpaRepository<VariantValue, VariantValueKey> {
}
