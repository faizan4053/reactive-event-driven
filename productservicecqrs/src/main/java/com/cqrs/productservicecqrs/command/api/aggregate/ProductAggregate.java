package com.cqrs.productservicecqrs.command.api.aggregate;

import com.cqrs.productservicecqrs.command.api.commands.CreateProductCommand;
import com.cqrs.productservicecqrs.command.api.events.ProductCreatedEvent;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        //can perform validations
        ProductCreatedEvent productCreatedEvent=new ProductCreatedEvent();
//                .builder()
//                .build();

        BeanUtils.copyProperties(createProductCommand,productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);
    }

    public ProductAggregate() {
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        this.name=productCreatedEvent.getName();
        this.price=productCreatedEvent.getPrice();
        this.quantity=productCreatedEvent.getQuantity();
        this.productId=productCreatedEvent.getProductId();
    }
}
