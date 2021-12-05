package com.mybank;

import com.mybank.entity.Account;
import com.mybank.entity.Customer;
import com.mybank.entity.Transaction;
import com.mybank.repository.AccountRepo;
import com.mybank.repository.JpaAccountRepo;
import com.mybank.repository.JpaCustomerRepo;
import com.mybank.repository.JpaTransactionRepo;
import com.mybank.web.AccountController;
import com.mybank.web.CustomerController;
import io.javalin.Javalin;
import org.apache.commons.lang3.RandomStringUtils;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config-> {
            config.enableCorsForAllOrigins();
        }).start(8000);
        app.get("/",ctx->ctx.result("Hello Manny How Are ya?!!"));
        /*              Rest Api         */
        /*          Control Customers    */

        //         view all customers
        app.get("/customers",CustomerController.viewCustomers);
        //       view a single customer knowing their customer-id
        app.get("customers/{customerId}",CustomerController.viewCustomer);

        /*          Control Accounts     */

        //       view all accounts associated with a customer
        app.get("/customers/{customerId}/accounts", AccountController.viewAccounts);
        //         view account details        //
        app.get("/customers/{customerId}/accounts/{acctNumber}", AccountController.viewAccount);
        //         delete account          //
//        app.delete("/customers/{customerId}/accounts/{acctNumber}/", AccountController.deleteAccount);
    }
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-pu");
//        JpaCustomerRepo jpaCustomerRepo = new JpaCustomerRepo(entityManagerFactory);
//        JpaAccountRepo jpaAccountRepo = new JpaAccountRepo(entityManagerFactory);
//        JpaTransactionRepo jpaTransactionRepo = new JpaTransactionRepo(entityManagerFactory);
//        Account acct1 = jpaAccountRepo.viewAccount("8909797657");
//        Account acct2 = jpaAccountRepo.viewAccount("6951023766");
//        Transaction trxn1 = new Transaction(acct2,acct1,500);
//        jpaTransactionRepo.addTransaction(trxn1);
//        System.out.println(trxn1);


    }

