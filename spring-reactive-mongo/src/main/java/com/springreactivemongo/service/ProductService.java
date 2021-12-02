package com.springreactivemongo.service;

import com.springreactivemongo.depository.ProductRepository;
import com.springreactivemongo.dto.ProductDto;
import com.springreactivemongo.entity.Product;
import com.springreactivemongo.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getProducts(){
        return productRepository
                .findAll()
                .delayElements(Duration.ofMillis(1000))
                .map(AppUtils::entityToProductDto);
    }

    public Mono<ProductDto> getProductById(String productId){
        return productRepository
                .findById(productId)
                .map(AppUtils::entityToProductDto);
    }

//    public Flux<ProductDto> getProductsByPriceRange(Double minPrice,Double maxPrice){
//        return productRepository
//                .findByPriceBetween(Range.closed(minPrice,maxPrice));
////                .map(AppUtils::entityToProductDto);
//    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        return productDtoMono
                .map(AppUtils::productDtoToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToProductDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono,String productId){
        return productRepository
                .findById(productId)
                .flatMap(p -> productDtoMono.map(AppUtils::productDtoToEntity)
                .doOnNext(e -> e.setProductId(productId)))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToProductDto);
    }

    public Mono<Void> deleteProduct(String productId){
        return productRepository.deleteById(productId);
    }
}
