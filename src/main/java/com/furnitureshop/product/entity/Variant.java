package com.furnitureshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.common.entity.BaseEntity;
import com.furnitureshop.importer.entity.ImporterDetail;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "import_price")
    private Integer importPrice = 0;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private Set<Value> values = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "variant")
    private Set<ImporterDetail> imports = new HashSet<>();
}
