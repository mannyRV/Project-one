package com.mybank.web;

import com.mybank.entity.Customer;
import com.mybank.repository.JpaCustomerRepo;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CustomerController {
    static EntityManagerFactory entityManagerFactory;
    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("my-pu");
    }

    static JpaCustomerRepo jpaCustomerRepo = new JpaCustomerRepo(entityManagerFactory);
//    public static Handler createCustomer = ctx -> {
//        String customerId= ctx.pathParam("customerId");
//        System.out.println(customerId);
//        Customer customer = ctx.bodyAsClass(Customer.class);
//        jpaCustomerRepo.addCustomer(customerId);
//        ctx.status(HttpStatus.CREATED_201);
//    };

    public static Handler viewCustomer = ctx -> {
        int customerId= Integer.parseInt(ctx.pathParam("customerId"));
        Customer customer=jpaCustomerRepo.viewCustomer(customerId);
        if(customer==null){
            ctx.status(HttpStatus.NOT_FOUND_404);
        }
        ctx.json(customer);
    };
    public static Handler viewCustomers = ctx -> {
        List<Customer> customers=jpaCustomerRepo.viewCustomers();
        ctx.json(customers);
    };

    public static Handler deleteCustomer = ctx -> {
        int customerId = Integer.parseInt(ctx.pathParam("customerId"));
        jpaCustomerRepo.deleteCustomer(customerId);
        ctx.status(HttpStatus.OK_200);
    };

}
