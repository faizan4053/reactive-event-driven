package com.springreactivemongo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String productId;
    private String productName;
    private Integer productQty;
    private Double productPrice;

}
