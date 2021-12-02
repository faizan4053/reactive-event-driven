package com.reactivepogramming.javareactivepogrammingdemo.servicestest;

import com.reactivepogramming.javareactivepogrammingdemo.services.MonoAndFluxServices;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class MonoAndFluxServicesTest {
    MonoAndFluxServices monoAndFluxServices
            =new MonoAndFluxServices();

    @Test
    void fruitFlux(){
        var fruitFlux=monoAndFluxServices.fruitFlux();

        StepVerifier.create(fruitFlux)
                .expectNext("Mongo","Banana","Apple")
                .verifyComplete()
//                .verifyError()
        ;
    }

    @Test
    void fruitFluxMap(){
        var fruitFlux=monoAndFluxServices.fruitFluxMap();

        StepVerifier.create(fruitFlux)
                .expectNext("MANGO","BANANA","APPLE")
                .verifyComplete()
        ;
    }

    @Test
    void fruitFluxFilter(){
        var fruitFlux=monoAndFluxServices.fruitFluxFilter(5);

        StepVerifier.create(fruitFlux)
                .expectNext("MANGO","BANANA","APPLE")
                .verifyComplete()
        ;
    }

    @Test
    void fruitFluxFlatMap(){
        var fruitFlux=monoAndFluxServices.fruitFluxFlatMap();

        StepVerifier.create(fruitFlux)
                .expectNextCount(16)
                .verifyComplete()
        ;
    }

    @Test
    void fruitFluxFlatMapAsync(){
        var fruitFlux=monoAndFluxServices.fruitFluxFlatMapAsync();

        StepVerifier.create(fruitFlux)
                .expectNextCount(16)
                .verifyComplete()
        ;
    }

    @Test
    void fruitFluxConcatMap(){
        var fruitFlux=monoAndFluxServices.fruitFluxConcatMap();

        StepVerifier.create(fruitFlux)
                .expectNextCount(16)
                .verifyComplete()
        ;
    }

    @Test
    void fruitFluxTransform(){
        var fruitFlux=monoAndFluxServices.fruitFluxTransform(5);

        StepVerifier.create(fruitFlux)
                .expectNext("Mango","Banana","Apple")
                .verifyComplete()
        ;
    }

    @Test
    void fruitFluxTransformDefaultIfEmpty(){
        var fruitFlux=monoAndFluxServices.fruitFluxTransformDefaultIfEmpty(10);

        StepVerifier.create(fruitFlux)
                .expectNext("default")
                .verifyComplete()
        ;
    }

    @Test
    void fruitFluxTransformSwitchIfEmpty(){
        var fruitFlux=monoAndFluxServices.fruitFluxTransformSwitchIfEmpty(6);

        StepVerifier.create(fruitFlux)
                .expectNextCount(1)
                .verifyComplete()
        ;
    }

    @Test
    void fruitMono(){
         var fruitMono=monoAndFluxServices.fruitMono();

         StepVerifier.create(fruitMono).expectNext("Apple")
                 .verifyComplete();
    }

    @Test
    void fruitMonoFlatMap(){
        var fruitMono=monoAndFluxServices.fruitMonoFlatMap();

        StepVerifier.create(fruitMono).expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMapMany(){
        var fruitMono=monoAndFluxServices.fruitMonoFlatMapMany();

        StepVerifier.create(fruitMono)
                .expectNextCount(5)
                .verifyComplete();
    }

}
