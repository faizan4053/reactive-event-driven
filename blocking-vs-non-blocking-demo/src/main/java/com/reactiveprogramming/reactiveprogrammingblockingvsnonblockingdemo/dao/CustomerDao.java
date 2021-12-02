package com.reactiveprogramming.reactiveprogrammingblockingvsnonblockingdemo.dao;

import com.reactiveprogramming.reactiveprogrammingblockingvsnonblockingdemo.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void delayExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers(){
       return IntStream.range(1,11)
               .peek(CustomerDao::delayExecution)
                .peek(customer -> System.out.println("customer number= " + customer))
                .mapToObj(customer -> new Customer(customer,"customer"+customer))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream(){
        return Flux.range(1,50)
                .delayElements(Duration.ofMillis(1000))
                .doOnNext(customer -> System.out.println("customer number in pipeline= " + customer))
                .map(customer -> new Customer(customer,"customer"+customer));
    }

    public Flux<Customer> getCustomerList(){
        return Flux.range(1,50)
                .doOnNext(customer -> System.out.println("customer number in pipeline= " + customer))
                .map(customer -> new Customer(customer,"customer"+customer));
    }
}
