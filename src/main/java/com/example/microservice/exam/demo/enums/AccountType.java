package com.example.microservice.exam.demo.enums;

public enum  AccountType {
        S("Savings"),
        C("Checking");

        private final String description;

        AccountType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
}
