package entities.dto;

import entities.BankCustomer;

public class BankCustomerDTO {
    int bankCustomerId;
    String fullName;
    String accountNumber;
    double balance;

    public BankCustomerDTO(BankCustomer bankCustomer) {
        this.bankCustomerId = bankCustomer.getId();
        this.fullName = bankCustomer.getFirstName() + " " + bankCustomer.getLastName();
        this.accountNumber = bankCustomer.getAccountNumber();
        this.balance = bankCustomer.getBalance();
    }

    public int getBankCustomerId() {
        return bankCustomerId;
    }

    public void setBankCustomerId(int bankCustomerId) {
        this.bankCustomerId = bankCustomerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
