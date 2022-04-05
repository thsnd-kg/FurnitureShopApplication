package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.entity.OptionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, OptionPK> {
}
