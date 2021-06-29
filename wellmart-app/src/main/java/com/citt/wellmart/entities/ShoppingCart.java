package com.citt.wellmart.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private  Customer customer ;

    @OneToMany(mappedBy = "shoppingCart")
    private List<Product> products;
}
