package com.citt.wellmart.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "SHIPPING_INFO")
public class ShippingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id ;
    private  String shippingType;
    private  String description;
    private  double shippingCost ;

    @OneToMany(mappedBy = "shippingInfo")
    private List<Order> orders;
}
