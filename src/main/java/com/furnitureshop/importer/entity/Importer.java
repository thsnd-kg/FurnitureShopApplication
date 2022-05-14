package com.furnitureshop.importer.entity;

import com.furnitureshop.common.entity.BaseEntity;
import com.furnitureshop.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Importer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "import_id")
    private Long importId;

    @Column(name = "import_desc")
    private String importDesc;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "is_deleted",
            columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "importer", cascade = CascadeType.ALL)
    private Set<ImporterDetail> importDetails = new HashSet<>();
}
