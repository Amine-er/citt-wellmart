package com.citt.wellmart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "COUNTRY")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;
    private String name ;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "country")
    List<City>  cities ;

}
