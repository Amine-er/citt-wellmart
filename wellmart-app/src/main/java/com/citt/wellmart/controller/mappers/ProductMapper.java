package com.citt.wellmart.controller.mappers;

import com.citt.wellmart.controller.models.ProductDto;
import com.citt.wellmart.entities.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    Product toProduct(ProductDto productDto);
    ProductDto toProductDto(Product product);
}
