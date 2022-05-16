package com.furnitureshop.product.repository;

import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.search.ProductPage;
import com.furnitureshop.product.search.ProductSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ProductCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Product> findAllWithFilters(ProductPage productPage, ProductSearchCriteria productSearchCriteria) {
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        Predicate predicate = getPredicate(productSearchCriteria, productRoot);
        criteriaQuery.where(predicate);
        setOrder(productPage, criteriaQuery, productRoot);

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(productPage.getPageNumber() * productPage.getPageSize());
        typedQuery.setMaxResults(productPage.getPageSize());

        Pageable pageable = getPageable(productPage);

        return new PageImpl<>(typedQuery.getResultList(), pageable, typedQuery.getResultList().size());
    }

    private Predicate getPredicate(ProductSearchCriteria productSearchCriteria, Root<Product> productRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(productSearchCriteria.getProductName())) {
            predicates.add(criteriaBuilder.like(productRoot.get("productName"), "%" + productSearchCriteria.getProductName() + "%"));
        }

        if (Objects.nonNull(productSearchCriteria.getCategoryId())) {
            if (!productSearchCriteria.getCategoryId().isEmpty()) {
                productRoot.fetch("category", JoinType.LEFT);
                predicates.add(criteriaBuilder.in(productRoot.get("category").get("categoryId")).value(productSearchCriteria.getCategoryId()));
            }
        }

        if (Objects.nonNull(productSearchCriteria.getBrandId())) {
            if (!productSearchCriteria.getBrandId().isEmpty()) {
                productRoot.fetch("brand", JoinType.LEFT);
                predicates.add(criteriaBuilder.in(productRoot.get("brand").get("brandId")).value(productSearchCriteria.getBrandId()));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(ProductPage productPage, CriteriaQuery<Product> criteriaQuery, Root<Product> productRoot) {
        if (productPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get(productPage.getSortBy().field)));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get(productPage.getSortBy().field)));
        }
    }

    private Pageable getPageable(ProductPage productPage) {
        Sort sort = Sort.by(productPage.getSortDirection(), productPage.getSortBy().field);
        return PageRequest.of(productPage.getPageNumber(), productPage.getPageSize(), sort);
    }
}
