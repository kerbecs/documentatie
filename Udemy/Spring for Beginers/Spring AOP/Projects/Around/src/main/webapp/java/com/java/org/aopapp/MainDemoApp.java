package com.java.org.aopapp;

import com.java.org.DAO.AccountDAO;
import com.java.org.DAO.MembershipDAO;
import com.java.org.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class MainDemoApp {
    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService fortuneService = context.getBean("trafficFortuneService",TrafficFortuneService.class);

        System.out.println("\nMain Program");
        System.out.println("Calling getFortune");

        boolean tripWire = true;
        String data = fortuneService.getFortune(tripWire);

        System.out.println("My fortune is: "+data);
        System.out.println("Finished");

        context.close();
    }
}
