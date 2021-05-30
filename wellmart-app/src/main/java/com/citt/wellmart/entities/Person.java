package com.citt.wellmart.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@Data
@ToString
@DiscriminatorValue("P")
public class Person  extends Merchant{
    private String firstName;
    private String lastName;
}
