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

        //Rest Api

        app.get("/customers",CustomerController.viewCustomers);
        app.get("customers/{customerId}",CustomerController.viewCustomer);
        app.get("/customers/{customerId}/accounts", AccountController.viewAccounts);

    }
}
