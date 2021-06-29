package com.citt.wellmart.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@ToString(callSuper = true)
@DiscriminatorValue("C")
public class Company extends Merchant{
    private String name ;
    private String activity;


}
