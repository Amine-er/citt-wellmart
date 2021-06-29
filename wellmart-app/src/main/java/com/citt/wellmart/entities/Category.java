package com.citt.wellmart.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  String name ;
    private  String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products ;

}
