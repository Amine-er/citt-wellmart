package com.citt.wellmart.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name ;
    private String description;
    private String imageUrl ;
    private Double price ;

    @Enumerated(EnumType.STRING)
    private ProductStatus status ;

    @ManyToOne
    @JoinColumn(name = "shoppingCart_id")
    private  ShoppingCart shoppingCart ;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant ;

    @OneToMany(mappedBy = "product")
    private List<ProductExperience> productExperiences;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "orderDetail_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<OrderDetail> orderDetails;
}
