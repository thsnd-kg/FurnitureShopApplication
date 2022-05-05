package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.entity.Variant;
import org.aspectj.weaver.ast.Var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    List<Variant> getProductVariantByProduct(Product product);
}
