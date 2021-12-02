package com.reactivepogramming.javareactivepogrammingdemo.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class MonoAndFluxServices {

    public Flux<String> fruitFlux(){
        return Flux.fromIterable(List.of("Mongo","Banana","Apple")).log();
    }

    public Flux<String> fruitFluxMap(){
        return Flux.fromIterable(List.of("Mango","Banana","Apple"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitFluxFilter(int num){
        return Flux.fromIterable(List.of("Mango","Banana","Apple"))
                .map(String::toUpperCase)
                .filter(s -> s.length() >=num);
    }

    public Flux<String> fruitFluxFlatMap(){
        return Flux.fromIterable(List.of("Mango","Banana","Apple"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitFluxFlatMapAsync(){
        return Flux.fromIterable(List.of("Mango","Banana","Apple"))
                .flatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ))
                .log();
    }

    //preserve the ordering unlike FlatMap
    public Flux<String> fruitFluxConcatMap(){
        return Flux.fromIterable(List.of("Mango","Banana","Apple"))
                .concatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ))
                .log();
    }

    public Flux<String> fruitFluxTransform(int num){
        Function<Flux<String>,Flux<String>> filterData
                = data -> data.filter(s -> s.length() >= num);

        return Flux.fromIterable(List.of("Mango","Banana","Apple"))
                .transform(filterData)
                .log();
    }

    public Flux<String> fruitFluxTransformDefaultIfEmpty(int num){
        Function<Flux<String>,Flux<String>> filterData
                = data -> data
                .filter(s -> s.length() >= num);

        return Flux.fromIterable(List.of("Mango","Banana","Apple"))
                .transform(filterData)
                .defaultIfEmpty("default")
                .log();
    }

    public Flux<String> fruitFluxTransformSwitchIfEmpty(int num){
        Function<Flux<String>,Flux<String>> filterData
                = data -> data
                .filter(s -> s.length() >= num);

        return Flux.fromIterable(List.of("Mango","Banana","Apple"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("pineapple","guava"))
                .transform(filterData)
                .log();
    }

    public Mono<String> fruitMono(){
        return Mono.just("Apple").log();
    }

    public Mono<List<String>> fruitMonoFlatMap(){
        return Mono.just("Apple")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public Flux<String> fruitMonoFlatMapMany(){
        return Mono.just("Apple")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }

    public static void main(String [] a){
        MonoAndFluxServices monoAndFluxServices
                =new MonoAndFluxServices();
        monoAndFluxServices.fruitFlux()
                .subscribe(f -> {
                    System.out.println("f = " + f);
                });
        monoAndFluxServices.fruitMono()
                .subscribe(f -> System.out.println("Mono f = " + f));
    }
}
