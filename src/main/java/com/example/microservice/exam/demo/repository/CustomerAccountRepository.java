package com.example.microservice.exam.demo.repository;

import com.example.microservice.exam.demo.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
    //CustomerAccount findByCustomerNumber(Long customerNumber);
}
