package com.furnitureshop.importer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.product.entity.ProductVariant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ImportDetailPK.class)
@Getter
@Setter
public class ImportDetail {
    @Column(name = "import_id")
    @Id
    private Long importId;

    @Column(name = "product_id")
    @Id
    private Long productId;

    @Column(name = "variant_id")
    @Id
    private Long variantId;

    @Column(name = "quantity", length = 50)
    private String quantity;

    @Column(name = "price")
    private Integer price;

    @JsonIgnore
    @ManyToOne
    private Import anImport;

    @JsonIgnore
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "variant_id", referencedColumnName = "variant_id", nullable = false, insertable = false, updatable = false)
    })
    private ProductVariant productVariant;
}
