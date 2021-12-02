package com.cqrs.productservicecqrs.command.api.events;

import com.cqrs.productservicecqrs.command.api.entity.Product;
import com.cqrs.productservicecqrs.command.api.repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    private ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @EventHandler
    public  void on(ProductCreatedEvent event){
        Product product =
                new Product();
        BeanUtils.copyProperties(event,product);
        productRepository.save(product);
    }
}
