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

        List<Account> list = new ArrayList<>();
        list.add(new Account("Jame","Gold"));
        list.add(new Account("Jacklin","Platinum"));
        list.add(new Account("Bree","Gold"));

        accountDAO.setAccountList(list);


        System.out.println("\nMain Program Before \n"+accountDAO.getAccountList());


        context.close();
    }
}
