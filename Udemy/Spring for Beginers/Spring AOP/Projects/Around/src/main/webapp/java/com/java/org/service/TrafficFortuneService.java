package com.java.org.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TrafficFortuneService {
    public String getFortune(boolean tripWire)  {
        if(tripWire)
            throw new RuntimeException("Something's wrong");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Except heavy traffic this morning";
    }
}
