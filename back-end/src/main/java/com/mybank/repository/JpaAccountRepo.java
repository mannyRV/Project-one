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
        String qstr = "FROM Account A WHERE A.number =:accountNumber";
        Query query= entityManager.createQuery(qstr,Account.class);
        query.setParameter("accountNumber",accountNumber);
        Account account = new Account();
        account = (Account) query.getSingleResult();
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
}
