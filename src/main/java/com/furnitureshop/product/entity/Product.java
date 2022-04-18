package com.furnitureshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name",
            length = 50)
    private String productName;

    @Column(name = "product_description",
            length = 100)
    private String productDescription;

    @Column(name = "image", length = 300)
    private String image;

    @ManyToOne
    @JoinColumn(name = "brand_id",
            referencedColumnName = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id",
            referencedColumnName = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Collection<ProductVariant> productVariants;
}
