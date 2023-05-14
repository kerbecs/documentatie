package com.java.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Component("utility")
@EnableTransactionManagement(proxyTargetClass = true)
public class Utility {
    @Autowired
    private ServiceImpl service;

    public ServiceImpl getService() {
        return service;
    }

    public void setService(ServiceImpl service) {
        this.service = service;
    }
}
