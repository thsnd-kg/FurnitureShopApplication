package com.furnitureshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.importer.entity.ImporterDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VariantValue> variantValues = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "productVariant")
    private List<ImporterDetail> imports;
}
