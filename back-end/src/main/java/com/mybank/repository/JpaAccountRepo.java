package com.mybank.repository;

import com.mybank.entity.Account;
import com.mybank.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class JpaAccountRepo implements AccountRepo{
    private EntityManagerFactory entityManagerFactory;

    public JpaAccountRepo(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void addAccount(Account account) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Account viewAccount(String accountNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String qstr = "FROM Account a WHERE a.number =:actnumber";
        Query query= entityManager.createQuery(qstr,Account.class);
        Account account = (Account) query.setParameter("actnumber",accountNumber).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return account;
    }
    @Override
    public List<Account> viewAccounts(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String qstr = "from Account a where a.owner.id = :id";
        Query query= entityManager.createQuery(qstr);
        List<Account> accounts = query.setParameter("id",customer.getId()).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return accounts;
    }

    @Override
    public void closeAccount(String accountNumber) {
        Account acct = new Account(accountNumber);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Account.class,accountNumber));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void updateDebit(Account account, double amount) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String number = account.getNumber();
        double newBal = account.getBalance()-amount;
        String hql = "UPDATE Account a set a.balance = :newBal WHERE a.number = :number";
        Query query = entityManager.createQuery(hql);
        query.setParameter("newBal",newBal).setParameter("number",number);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void updateCredit(Account account, double amount) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String number = account.getNumber();
        double newBal = account.getBalance()+amount;
        String hql = "UPDATE Account a set a.balance = :newBal WHERE a.number = :number";
        Query query = entityManager.createQuery(hql);
        query.setParameter("newBal",newBal).setParameter("number",number);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
