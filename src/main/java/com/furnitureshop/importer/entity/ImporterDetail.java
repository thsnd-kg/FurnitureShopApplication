package com.furnitureshop.importer.entity;

import com.furnitureshop.product.entity.Variant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(ImporterDetailPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImporterDetail {
    @Id
    @Column(name = "import_id")
    private Long importId;

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "variant_id")
    private Long variantId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "import_id", referencedColumnName = "import_id", insertable = false, updatable = false)
    private Importer importer;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false),
            @JoinColumn(name = "variant_id", referencedColumnName = "variant_id", insertable = false, updatable = false)
    })
    private Variant variant;
}
