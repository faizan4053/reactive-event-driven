package com.cqrs.productservicecqrs.command.api.controller;

import com.cqrs.productservicecqrs.command.api.commands.CreateProductCommand;
import com.cqrs.productservicecqrs.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }


    @PostMapping
    public String addProduct(@RequestBody ProductRestModel model) {

        CreateProductCommand createProductCommand=
                CreateProductCommand
                        .builder()
                        .productId(UUID.randomUUID().toString())
                        .name(model.getName())
                        .price(model.getPrice())
                        .quantity(model.getQuantity())
                        .build();
        String res=commandGateway.sendAndWait(createProductCommand);
        return res;
    }
}
