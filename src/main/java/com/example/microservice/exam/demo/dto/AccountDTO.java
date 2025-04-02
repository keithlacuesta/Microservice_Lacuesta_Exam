package com.example.microservice.exam.demo.dto;

import com.example.microservice.exam.demo.enums.AccountType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountDTO {
    @NotBlank(message = "Customer name is required")
    @Size(max = 50, message = "Customer name cannot exceed 50 characters")
    private String customerName;

    @NotBlank(message = "Customer mobile is required")
    @Size(max = 20, message = "Customer mobile cannot exceed 20 characters")
    private String customerMobile;

    @NotBlank(message = "Customer email is required")
    @Email(message = "Invalid email format")
    @Size(max = 50, message = "Email cannot exceed 50 characters")
    private String customerEmail;

    @NotBlank(message = "Address1 is required")
    @Size(max = 100, message = "Address1 cannot exceed 100 characters")
    private String address1;

    @Size(max = 100, message = "Address2 cannot exceed 100 characters")
    private String address2; // Optional field

    @NotNull(message = "Account type is required")
    private AccountType accountType;

    public AccountDTO() {}

    public AccountDTO(String customerName, String customerMobile, String customerEmail,
                      String address1, String address2, AccountType accountType) {
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.customerEmail = customerEmail;
        this.address1 = address1;
        this.address2 = address2;
        this.accountType = accountType;
    }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerMobile() { return customerMobile; }
    public void setCustomerMobile(String customerMobile) { this.customerMobile = customerMobile; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getAddress1() { return address1; }
    public void setAddress1(String address1) { this.address1 = address1; }

    public String getAddress2() { return address2; }
    public void setAddress2(String address2) { this.address2 = address2; }

    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }
}
