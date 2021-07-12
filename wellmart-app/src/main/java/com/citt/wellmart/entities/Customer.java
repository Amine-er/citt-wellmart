package com.citt.wellmart.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String name ;
    private String email;
    private String phone;
    private String password;

   @ManyToOne
    @JoinColumn(name = "city_id")
    private City city ;

    @OneToOne(mappedBy = "customer")
    private ShoppingCart shoppingCart ;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();


}
