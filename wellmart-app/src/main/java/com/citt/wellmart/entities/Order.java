package com.citt.wellmart.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateCreate ;
    private LocalDateTime dateShipped;

    @Enumerated(EnumType.STRING)
    private OrderStatus status ;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "shippingInfo_id")
    private ShippingInfo shippingInfo;

    @OneToOne(mappedBy = "order")
    private OrderDetail orderDetail;

    @ManyToOne
    @JoinColumn(name = "deliveryMan_id")
    private DeliveryMan deliveryMan;

}
