package com.citt.wellmart.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "PRODUCT_EXPERIENCE")
public class ProductExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reviewTitle;
    private String reviewBody;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
