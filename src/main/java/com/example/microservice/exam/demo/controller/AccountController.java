package com.example.microservice.exam.demo.controller;

import com.example.microservice.exam.demo.dto.AccountDTO;
import com.example.microservice.exam.demo.dto.CustomerAccountResponse;
import com.example.microservice.exam.demo.entity.CustomerAccount;
import com.example.microservice.exam.demo.exception.AccountCreationResponse;
import com.example.microservice.exam.demo.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountCreationResponse> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        CustomerAccount account = accountService.createAccount(accountDTO);

        AccountCreationResponse response = new AccountCreationResponse();
        response.setCustomerNumber(account.getId());
        response.setTransactionStatusCode(201);
        response.setTransactionStatusDescription("Customer account created");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<CustomerAccountResponse> getCustomerAccount(@PathVariable Long customerNumber) {
        CustomerAccountResponse response = accountService.getCustomerAccount(customerNumber);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}
