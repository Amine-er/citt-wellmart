package com.citt.wellmart.controller.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MerchantDto {
    private String merchantType;
    private Long id ;
    protected String description;
    protected String address ;
    protected String email;
    protected String phone;
    protected String webSite;
    protected String name ;
    protected String activity;
    protected String firstName;
    protected String lastName;
}
