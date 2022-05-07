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
public class Variant extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "variant_id")
    private Long variantId;

    @Column(name = "price")
    private Integer price;

    @Column(name = "image",
            length = 300)
    private String image;

    @Column(name = "quantity",
            columnDefinition = "integer default 0")
    private Integer quantity = 0;

    @Column(name = "sku",
            length = 50)
    private String sku;

    @Column(name = "is_deleted",
            columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    @OneToMany(mappedBy = "variant")
    private List<Value> values;

    @JsonIgnore
    @OneToMany(mappedBy = "variant")
    private List<ImporterDetail> imports;
}
