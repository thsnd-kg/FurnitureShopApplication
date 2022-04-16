package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.entity.OptionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<Option, OptionPK> {
    @Query("SELECT o FROM Option o WHERE o.optionId = ?1")
    List<Option> findByOptionId(Long optionId);

    @Query("SELECT o FROM Option o WHERE o.categoryId = ?1")
    List<Option> findByCategoryId(Long categoryId);

    @Query("SELECT o FROM Option o WHERE o.optionId = ?1 AND o.categoryId = ?2")
    Optional<Option> findById(Long optionId, Long categoryId);
}
