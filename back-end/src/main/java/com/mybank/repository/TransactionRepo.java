package com.mybank.repository;

import com.mybank.entity.Account;
import com.mybank.entity.Customer;
import com.mybank.entity.Transaction;

import java.util.List;

public interface TransactionRepo {
    void addTransaction(Transaction transaction);
    List<Transaction> viewTransactions(Account account);
}
