package com.example.microservice.exam.demo.service;

import com.example.microservice.exam.demo.dto.AccountDTO;
import com.example.microservice.exam.demo.dto.CustomerAccountResponse;
import com.example.microservice.exam.demo.dto.SavingsAccountResponse;
import com.example.microservice.exam.demo.entity.CustomerAccount;
import com.example.microservice.exam.demo.entity.SavingsAccount;
import com.example.microservice.exam.demo.exception.CustomerNotFoundException;
import com.example.microservice.exam.demo.repository.CustomerAccountRepository;
import com.example.microservice.exam.demo.repository.SavingsAccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final CustomerAccountRepository repository;
    private final SavingsAccountRepository savingsAccountRepository;

    public AccountService(CustomerAccountRepository repository, SavingsAccountRepository savingsAccountRepository) {
        this.repository = repository;
        this.savingsAccountRepository = savingsAccountRepository;
    }

    @Transactional
    public CustomerAccount createAccount(AccountDTO dto) {
        CustomerAccount account = new CustomerAccount();
        account.setCustomerName(dto.getCustomerName());
        account.setCustomerMobile(dto.getCustomerMobile());
        account.setCustomerEmail(dto.getCustomerEmail());
        account.setAddress1(dto.getAddress1());
        account.setAddress2(dto.getAddress2());
        account.setAccountType(dto.getAccountType());

        CustomerAccount savedCustomer = repository.save(account);

        SavingsAccount savings = new SavingsAccount();
        savings.setAccountType(dto.getAccountType());
        savings.setAvailableBalance(100.0);
        savings.setCustomerAccount(savedCustomer);

        savingsAccountRepository.save(savings);

        return repository.save(account);
    }

    public CustomerAccountResponse getCustomerAccount(Long customerNumber) {
        CustomerAccount customerAccount = repository.findById(customerNumber)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        List<SavingsAccountResponse> savingsResponses = customerAccount.getBankAccounts().stream()
                .map(account -> new SavingsAccountResponse(
                        account.getAccountNumber(),
                        account.getAccountTypeAsString(),
                        account.getAvailableBalance()))
                .collect(Collectors.toList());

        CustomerAccountResponse response = new CustomerAccountResponse();
        response.setCustomerNumber(customerAccount.getId());
        response.setCustomerName(customerAccount.getCustomerName());
        response.setCustomerMobile(customerAccount.getCustomerMobile());
        response.setCustomerEmail(customerAccount.getCustomerEmail());
        response.setAddress1(customerAccount.getAddress1());
        response.setAddress2(customerAccount.getAddress2());
        response.setSavings(savingsResponses);
        response.setTransactionStatusCode(302);
        response.setTransactionStatusDescription("Customer Account found");

        return response;
    }
}
