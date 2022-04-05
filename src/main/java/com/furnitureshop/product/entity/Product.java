package com.furnitureshop.product.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Basic
    @Column(name = "product_name",
            length = 50,
            unique = true)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Collection<ProductVariant> productVariants;
}
