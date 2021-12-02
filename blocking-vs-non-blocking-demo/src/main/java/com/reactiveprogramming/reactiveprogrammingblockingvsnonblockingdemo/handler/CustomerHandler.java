package com.reactiveprogramming.reactiveprogrammingblockingvsnonblockingdemo.handler;

import com.reactiveprogramming.reactiveprogrammingblockingvsnonblockingdemo.dao.CustomerDao;
import com.reactiveprogramming.reactiveprogrammingblockingvsnonblockingdemo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request){
        Flux<Customer> customers=customerDao.getCustomerList();
        return ServerResponse.ok().body(customers,Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request){
        int customerId=Integer.valueOf(request.pathVariable("input"));

//        customerDao.getCustomerList().filter(c -> c.getId()==customerId).take(1).single();

        Mono<Customer> customer=customerDao.getCustomerList().filter(c -> c.getId()==customerId).next();
        return ServerResponse.ok().body(customer,Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customer=request.bodyToMono(Customer.class);
        Mono<String> saveResponse=customer.map(c -> c.getId()+":"+c.getName());
        return ServerResponse.ok().body(saveResponse,String.class);
    }

}
