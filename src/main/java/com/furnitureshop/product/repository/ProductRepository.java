package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.Product;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.isDeleted = FALSE")
    @NonNull
    Page<Product> findByIsDeletedFalse(@NonNull Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1% AND p.isDeleted = FALSE")
    Page<Product> findByProductName(String productName, Pageable pageRequest);

    @Modifying
    @Transactional
    @Query("UPDATE Variant v SET v.isDeleted = TRUE WHERE v.product.productId = ?1")
    void deleteVariantByProductId(Long productId);
}
