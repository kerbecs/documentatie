package com.java.org.aopapp;

import com.java.org.DAO.AccountDAO;
import com.java.org.DAO.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainDemoApp {
    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountDAO = context.getBean("accountDAO",AccountDAO.class);

        List<Account> accountList = accountDAO.findAccounts();

        System.out.println("\nMain Program AfterReturning \n"+accountList);

        context.close();
    }
}
