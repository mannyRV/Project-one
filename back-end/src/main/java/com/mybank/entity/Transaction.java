package com.mybank.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

import com.mybank.repository.JpaAccountRepo;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    private int t_id;


    @Temporal(TemporalType.DATE)
    public Date date;

    @ManyToOne
    @JsonBackReference
    private Account fromAccount;

    @Column(name = "to_account_number")
    private String toAccount;

    private double amount;


    //preform the transfer of funds and update accounts in database
    private void transfer(Account act1, Account act2, double v){
        // create em factory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-pu");
        // create jpa acct repo instance using em factory
        JpaAccountRepo jpaAccountRepo = new JpaAccountRepo(entityManagerFactory);
        // perform debit
       //act1.setBalance(act1.getBalance()-v);
        // perform debit in database
        jpaAccountRepo.updateDebit(act1,v);
        //perform credit
        //act2.setBalance(act2.getBalance()+v);
        // perform credit in database
        jpaAccountRepo.updateCredit(act2,v);
    }
    // Constructor
    public Transaction(Account fromAccount, Account toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount.getNumber();
        this.amount = amount;
        this.date = java.util.Calendar.getInstance().getTime();
        transfer(fromAccount,toAccount,amount);
    }

    public Transaction() {
    }
    // Setters and getters

    public int getId() {
        return t_id;
    }

    public void setId(int id) {
        this.t_id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }
    //toString method for purpose of printing out the object info


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + t_id +
                ", date=" + date +
                ", fromAccount=" + fromAccount +
                ", toAccount='" + toAccount + '\'' +
                ", amount=" + amount +
                '}';
    }
}
