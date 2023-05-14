package com.java.org.aopapp;

import com.java.org.DAO.AccountDAO;
import com.java.org.DAO.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {
    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountDAO = context.getBean("accountDAO",AccountDAO.class);
        MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        membershipDAO.addAccount();

        Account account = new Account();
        account.setName("Jack");
        account.setLevel("SENIOR");
        accountDAO.addAccount(account,true);

        accountDAO.setName("Mititiuc");
        accountDAO.setServiceCode("silver");

        System.out.println(accountDAO.getName()+" "+accountDAO.getServiceCode());


        membershipDAO.goToSleep();
        accountDAO.doWork();

        context.close();
    }
}
