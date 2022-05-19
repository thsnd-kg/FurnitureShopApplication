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
@Setter
@Getter
public class Importer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "import_id")
    private Long importId;

    @Column(name = "import_desc")
    private String importDesc;

    @Transient
    private Integer totalPrice;

    public Integer getTotalPrice() {
        return importDetails.stream().mapToInt(i -> i.getPrice() * i.getQuantity()).sum();
    }

    @OneToMany(mappedBy = "importer", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Set<ImporterDetail> importDetails = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
