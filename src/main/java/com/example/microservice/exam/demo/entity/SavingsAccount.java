package com.example.microservice.exam.demo.entity;

import com.example.microservice.exam.demo.enums.AccountType;

import javax.persistence.*;

@Entity
@Table(name = "savings_accounts")
public class SavingsAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType; // Enum (SAVINGS, CHECKING)

    @Column(nullable = false)
    private Double availableBalance;

    @ManyToOne
    @JoinColumn(name = "customer_number", nullable = false)
    private CustomerAccount customerAccount;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public String getAccountTypeAsString() {
        return accountType == AccountType.S ? "Savings" : "Checking";
    }
}
