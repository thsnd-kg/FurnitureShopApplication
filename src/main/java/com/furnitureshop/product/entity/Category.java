package com.furnitureshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "category_id")
    protected Long categoryId;

    @Column(name = "category_name", length = 50)
    private String categoryName;

    @Column(name = "category_desc", length = 100)
    private String categoryDesc;

    @Column(name = "is_deleted",
            columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @JsonIgnore
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> children;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Option> options;
}
