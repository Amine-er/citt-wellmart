package com.citt.wellmart.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PAYMENT")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private String paymentCurrency;
    private String address;
    private Double total ;

    @OneToOne(mappedBy = "payment")
    private OrderDetail orderDetail ;
}
