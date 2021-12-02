package com.reactiveprogramming.reactiveprogrammingblockingvsnonblockingdemo.service;

import com.reactiveprogramming.reactiveprogrammingblockingvsnonblockingdemo.dao.CustomerDao;
import com.reactiveprogramming.reactiveprogrammingblockingvsnonblockingdemo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getCustomers(){
        long start=System.currentTimeMillis();
        List<Customer> customers=customerDao.getCustomers();
        long end=System.currentTimeMillis();
        System.out.println("Total time : "+(end-start));
        return customers;
    }

    public Flux<Customer> getCustomersStream(){
        long start=System.currentTimeMillis();
        Flux<Customer> customers=customerDao.getCustomersStream();
        long end=System.currentTimeMillis();
        System.out.println("Total time : "+(end-start));
        return customers;
    }
}
