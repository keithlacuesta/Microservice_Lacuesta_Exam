package com.example.microservice.exam.demo.entity;


import com.example.microservice.exam.demo.enums.AccountType;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "accounts")
public class CustomerAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_number")
    private Long customerNumber;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Customer name is required")
    private String customerName;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "Customer mobile is required")
    private String customerMobile;

    @Column(length = 50, nullable = false, unique = true)
    @NotBlank(message = "Customer email is required")
    @Email(message = "Invalid email format")
    private String customerEmail;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Address1 is required")
    private String address1;

    @Column(length = 100)
    private String address2;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Account type is required")
    private AccountType accountType;


    @OneToMany(mappedBy = "customerAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SavingsAccount> bankAccounts;

    public CustomerAccount() {
    }

    public CustomerAccount(String customerName, String customerMobile, String customerEmail, String address1, String address2, AccountType accountType) {
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.customerEmail = customerEmail;
        this.address1 = address1;
        this.address2 = address2;
        this.accountType = accountType;
    }

    public Long getId() {
        return customerNumber;
    }

    public void setId(Long id) {
        this.customerNumber = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public List<SavingsAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<SavingsAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

}
