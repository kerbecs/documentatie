package com.java.org.aopapp;

import com.java.org.DAO.AccountDAO;
import com.java.org.DAO.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class MainDemoApp {
    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountDAO = context.getBean("accountDAO",AccountDAO.class);

        List<Account> list = null;
        boolean tripWire = true;

        try {
            list = accountDAO.findAccounts(tripWire);
        }
        catch (Exception e){
            System.out.println("Exception caught in main Program: "+e);
        }
       System.out.println("Main Program after Throwing");

        context.close();
    }
}
