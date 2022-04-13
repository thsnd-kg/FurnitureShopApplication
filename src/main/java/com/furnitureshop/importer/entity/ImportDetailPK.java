package com.furnitureshop.importer.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ImportDetailPK implements Serializable {
    @Column(name = "import_id")
    @Id
    private Long importId;

    @Column(name = "product_id")
    @Id
    private Long productId;

    @Column(name = "variant_id")
    @Id
    private Long variantId;
}
