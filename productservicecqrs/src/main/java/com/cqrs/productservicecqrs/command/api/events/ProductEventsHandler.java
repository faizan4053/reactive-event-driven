package com.cqrs.productservicecqrs.command.api.events;

import com.cqrs.productservicecqrs.command.api.entity.Product;
import com.cqrs.productservicecqrs.command.api.repository.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {

    private ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @EventHandler
    public  void on(ProductCreatedEvent event) throws Exception {
        Product product =
                new Product();
        BeanUtils.copyProperties(event,product);
        productRepository.save(product);
        throw new Exception("Exception Occured!");
    }

    @ExceptionHandler
    public void handle(Exception e) throws Exception {
        throw e;
    }
}
