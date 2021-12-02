package com.springreactivemongo.controller;

import com.springreactivemongo.dto.ProductDto;
import com.springreactivemongo.entity.Product;
import com.springreactivemongo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<?> getProducts(){
        Flux<ProductDto> products=productService.getProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String productId){
        Mono<ProductDto> product=productService.getProductById(productId);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Mono<ProductDto> productDtoMono){
        Mono<ProductDto> product=productService.saveProduct(productDtoMono);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> saveProduct(@RequestBody Mono<ProductDto> productDtoMono,@PathVariable("id") String productId){
        Mono<ProductDto> product=productService.updateProduct(productDtoMono,productId);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId){
        Mono<Void> val=productService.deleteProduct(productId);
        return ResponseEntity.ok().body(val);
    }

}
