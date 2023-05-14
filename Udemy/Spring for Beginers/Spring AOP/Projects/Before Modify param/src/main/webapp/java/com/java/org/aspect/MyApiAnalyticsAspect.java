package com.java.org.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect extends AopEpressions{
    @Before("pointCut()")
    public void performApyAnalytics(){
        System.out.println("\n=========>>>> Performing API analytics");
    }
}
