package com.java.org.DAO;

import com.java.org.aopapp.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {
    private String name;
    private String serviceCode;

    public List<Account> findAccounts(){
        List<Account> myAccounts = new ArrayList<>();

        Account ac1 = new Account("John","Silver");
        Account ac2 = new Account("Jame","Gold");
        Account ac3 = new Account("Bree","Platinum");

        myAccounts.add(ac1);
        myAccounts.add(ac2);
        myAccounts.add(ac3);

        System.out.println("Return is next");

        return myAccounts;
    }

    public void addAccount(Account account, boolean vipFlag){
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }
    public boolean doWork(){
        System.out.println(getClass() + ": doWork()");

        return false;
    }

    public String getName() {
        System.out.println(getClass() + "  getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + "  setName()");

        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + "  getServiceCode()");

        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + "  setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
