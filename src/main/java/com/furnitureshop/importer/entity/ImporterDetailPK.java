package com.furnitureshop.importer.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class ImporterDetailPK implements Serializable {
    @Id
    @Column(name = "import_id")
    private Long importId;

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "variant_id")
    private Long variantId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImporterDetailPK that = (ImporterDetailPK) o;
        return importId.equals(that.importId) && productId.equals(that.productId) && variantId.equals(that.variantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(importId, productId, variantId);
    }
}