package com.mybank.repository;

import com.mybank.entity.Customer;

import java.util.List;

public interface CustomerRepo {
    void addCustomer(String name, String email, String password);
    void addCustomer(Customer customer);
    Customer viewCustomer(String email);
    Customer viewCustomer(int id);
    List<Customer> viewCustomers();
    void deleteCustomer(int id);
    void openAccount(Customer customer, double deposit);

}
