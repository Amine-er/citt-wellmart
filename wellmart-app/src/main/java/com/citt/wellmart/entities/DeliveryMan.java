package com.citt.wellmart.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "DELIVERY_MAN")
public class DeliveryMan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "deliveryMan")
    private List<Order> orders;
}
