package com.mybank.web;

import com.mybank.entity.Account;
import com.mybank.entity.Customer;
import com.mybank.repository.AccountRepo;
import com.mybank.repository.CustomerRepo;
import com.mybank.repository.JpaAccountRepo;
import com.mybank.repository.JpaCustomerRepo;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static java.lang.Integer.parseInt;

public class AccountController {
    static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("my-pu");
    }

    //-------------------------------------------------
    static AccountRepo acctRepo = new JpaAccountRepo(entityManagerFactory);
    static CustomerRepo customerRepo = new JpaCustomerRepo(entityManagerFactory);
    //--------------------------------------------------
//    public static Handler createAccount = ctx-> {
//        String
//    }

    //    viewAccounts;
    public static Handler viewAccounts = ctx -> {
        int custId = Integer.parseInt(ctx.pathParam("customerId"));
        Customer customer = customerRepo.viewCustomer(custId);
        if (customer == null) {
            ctx.status(HttpStatus.NOT_FOUND_404);
        }
        List<Account> accounts = acctRepo.viewAccounts(customer);
        ctx.json(accounts);
    };
    //    viewAccount;
    public static Handler viewAccount = ctx -> {
        String acctNumber = ctx.pathParam("acctNumber");
        Account account = acctRepo.viewAccount(acctNumber);
        int custId = Integer.parseInt(ctx.pathParam("customerId"));
        if (account.getOwner().getId()==custId) {
            ctx.json(account);
        } else {
            ctx.status(HttpStatus.NOT_FOUND_404);
        }
    };
    //delete account
//    public static Handler deleteAccount = ctx -> {
//        int custId = Integer.parseInt(ctx.pathParam("customerId"));
//
//        String acctNumber = ctx.pathParam("acctNumber");
//        Account account = acctRepo.viewAccount(acctNumber);
//        if (account.getOwner().getId()==custId) {
//            ctx.json(account);
//        } else {
//            ctx.status(HttpStatus.NOT_FOUND_404);
//        }
//    };
}
