package com.springreactivemongo.utils;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.springreactivemongo.dto.ProductDto;
import com.springreactivemongo.entity.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static ProductDto entityToProductDto(Product product){
        ProductDto productDto=new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        return productDto;
    }

    public static Product productDtoToEntity(ProductDto productDto){
        Product product=new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }
}
