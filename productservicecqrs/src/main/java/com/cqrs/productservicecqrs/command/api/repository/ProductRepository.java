package com.cqrs.productservicecqrs.command.api.repository;

import com.cqrs.productservicecqrs.command.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

}
