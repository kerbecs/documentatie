package com.java.org.DAO;

import com.java.org.aopapp.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {
    private String name;
    private String serviceCode;

    private List<Account> accountList = new ArrayList<>();



    public void addAccount(Account account, boolean vipFlag){
        accountList.add(account);
    }
    public boolean doWork(){
        System.out.println(getClass() + ": doWork()");

        return false;
    }

    public List<Account> findAccounts(boolean tripWire){
        if(tripWire){
            throw new RuntimeException("Something's wrong");
        }


        accountList.add(new Account("Jhon","Gold"));
        accountList.add(new Account("Ela","Diamante"));
        accountList.add(new Account("Bree","Iron"));

        return accountList;
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

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
