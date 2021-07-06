package com.citt.wellmart.controller.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class ProductDto {
    private String name ;
    private String description;
    private String imageUrl ;
    private String price ;
    private String categoryId;
    private UUID merchantId;

}
