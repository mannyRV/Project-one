package com.mybank.repository;

import com.mybank.entity.Account;
import com.mybank.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class JpaCustomerRepo implements CustomerRepo{

    private EntityManagerFactory entityManagerFactory;

    public JpaCustomerRepo(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void addCustomer(String name, String email, String password) {
        Customer customer = new Customer(name,email,password);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void addCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Customer viewCustomer(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer=entityManager.find(Customer.class,email);
        entityManager.getTransaction().commit();
        entityManager.close();
        return customer;
    }
    @Override
    public Customer viewCustomer(int customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String jpql = "from Customer c where c.id = :custid ";
        Query query=entityManager.createQuery(jpql);
        Customer customer = (Customer) query.setParameter("custid",customerId).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return customer;
    }

    @Override
    public List<Customer> viewCustomers(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String jpql = "from Customer";
        Query query=entityManager.createQuery(jpql);
        List<Customer> customers = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return customers;
    }
    @Override
    public void deleteCustomer(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Customer.class,id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Override
    public void openAccount(Customer customer, double deposit) {
        Account account = new Account(customer,deposit);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
