package com.citt.wellmart.controller.models;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ProductDto {
    private String name ;
    private String description;
    private String imageUrl ;
    private String price ;
    private String categoryId;
    private String merchantUserName;

}
