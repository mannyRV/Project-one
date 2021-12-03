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

//    viewAccount;
    public static Handler viewAccounts = ctx -> {
       int custId = Integer.parseInt(ctx.pathParam("customerId"));
       Customer customer = customerRepo.viewCustomer(custId);
       if (customer == null) {
              ctx.status(HttpStatus.NOT_FOUND_404);
        }
        List<Account> accounts = acctRepo.viewAccounts(customer);
        ctx.json(accounts);
};
//    viewAccounts;
//    closeAccount;
}
