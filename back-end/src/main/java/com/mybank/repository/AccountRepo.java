package com.mybank.repository;

import com.mybank.entity.Account;
import com.mybank.entity.Customer;

import java.util.List;

public interface AccountRepo {
    void addAccount(Account account);
    Account viewAccount(String accountNumber);
    List<Account> viewAccounts(Customer customer);
    void closeAccount(String accountNumber);
}
