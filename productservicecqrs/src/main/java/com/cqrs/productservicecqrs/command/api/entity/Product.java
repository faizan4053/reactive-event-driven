package com.cqrs.productservicecqrs.command.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
