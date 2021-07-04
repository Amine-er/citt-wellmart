package com.citt.wellmart.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private  String imageBase64;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products ;

}
