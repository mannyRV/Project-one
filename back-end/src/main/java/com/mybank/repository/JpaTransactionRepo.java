package com.mybank.repository;

import com.mybank.entity.Account;
import com.mybank.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class JpaTransactionRepo implements TransactionRepo{
    private EntityManagerFactory entityManagerFactory;

    public JpaTransactionRepo(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
    public void addTransaction(Transaction transaction) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(transaction);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Transaction> viewTransactions(Account account) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String jpql="FROM Transaction T WHERE T.fromAccount = :account";
        Query query=entityManager.createQuery(jpql,Transaction.class);
        List<Transaction> transactions=query.setParameter("account",account).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return  transactions;
    }
}
