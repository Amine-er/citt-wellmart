package com.citt.wellmart.entities;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="merchant_type",
        discriminatorType = DiscriminatorType.STRING)
@Data
@ToString
public abstract class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    protected String description;
    protected String address ;
    protected String email;
    protected String phone;
    protected String webSite;

    @OneToMany(mappedBy = "merchant",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    protected List<Product> products = new ArrayList<>();

}
