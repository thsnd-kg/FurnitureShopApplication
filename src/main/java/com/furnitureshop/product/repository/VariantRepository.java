package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    @Query("SELECT v FROM Variant v WHERE v.isDeleted = FALSE AND v.product.productId = ?1")
    List<Variant> findActiveByProductId(Long productId);

    @Query("SELECT v FROM Variant v WHERE  v.product.productId = ?1")
    List<Variant> findByProductId(Long productId);
}
