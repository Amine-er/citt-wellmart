package com.citt.wellmart.controller.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PersonDto {
    private String merchantType;
    protected String description;
    protected String address ;
    protected String email;
    protected String phone;
    protected String webSite;
    private String firstName;
    private String lastName;
}
