package com.cqrs.productservicecqrs.query.api.projections;

import com.cqrs.productservicecqrs.command.api.entity.Product;
import com.cqrs.productservicecqrs.command.api.model.ProductRestModel;
import com.cqrs.productservicecqrs.command.api.repository.ProductRepository;
import com.cqrs.productservicecqrs.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    @Autowired
    private ProductRepository productRepository;

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery){
        List<Product> products=
                productRepository.findAll();

        List<ProductRestModel> productRestModels=
                products.stream()
                        .map(product -> ProductRestModel
                                .builder()
                                .name(product.getName())
                                .price(product.getPrice())
                                .quantity(product.getQuantity())
                                .build())
                        .collect(Collectors.toList());
        return productRestModels;
    }
}
