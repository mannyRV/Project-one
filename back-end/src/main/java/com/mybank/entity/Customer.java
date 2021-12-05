package com.mybank.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    private int id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Account> ownedAccounts;

    public Customer( String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
