package com.furnitureshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.common.entity.BaseEntity;
import com.furnitureshop.importer.entity.ImporterDetail;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "variant_id")
    private Long variantId;

    @Column(name = "price",
            columnDefinition = "integer default 0")
    private Integer price;

    @Column(name = "image",
            length = 300)
    private String image;

    @Column(name = "quantity",
            columnDefinition = "integer default 0")
    private Integer quantity;

    @Column(name = "sku",
            length = 50)
    private String sku;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    @OneToMany(mappedBy = "productVariant")
    private List<VariantValue> variantValues;

    @JsonIgnore
    @OneToMany(mappedBy = "productVariant")
    private List<ImporterDetail> imports;
}
