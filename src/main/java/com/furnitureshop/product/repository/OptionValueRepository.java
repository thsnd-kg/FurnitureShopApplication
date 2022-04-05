package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.OptionValue;
import com.furnitureshop.product.entity.OptionValuePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionValueRepository extends JpaRepository<OptionValue, OptionValuePK> {
}
