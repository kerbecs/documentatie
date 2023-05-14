package com.java.org.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect extends AopEpressions{


    @Before("pointCut()")
    public void beforeAddAccountAdvice(){
        System.out.println("\n=========>>>> Executing before adding an AccountAdvice");
    }



}
