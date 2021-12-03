package com.mybank.entity;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="accounts")
public class Account {
    @Id
    private String number;
    private double balance;

    @ManyToOne
    private Customer owner;

    @OneToMany (mappedBy = "fromAccount",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Transaction> acctTransactions;

    public Customer getOwner() {
        return owner;
    }

    private String accountNumberGenerator() {
        int length = 10;
        boolean useLetters = false;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }
    //constructor
    public Account(Customer customer,double deposit) {
        this.balance = deposit;
        this.number= accountNumberGenerator();
        this.owner=customer;
    }

    public Account() {
    }

    public Account(String number) {
        this.number = number;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public List<Transaction> getAcctTransactions() {
        return acctTransactions;
    }

    public void setAcctTransactions(List<Transaction> acctTransactions) {
        this.acctTransactions = acctTransactions;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                '}';
    }
}
