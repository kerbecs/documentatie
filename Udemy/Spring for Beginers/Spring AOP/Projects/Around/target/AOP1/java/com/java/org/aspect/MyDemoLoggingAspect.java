package com.java.org.aspect;

import com.java.org.aopapp.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect extends AopEpressions{


    @AfterThrowing(pointcut = "execution(* com.java.org.DAO.*.*(..))",throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint,Throwable exception){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("-----------------After Throwing----------------------");
        System.out.println("Signature: "+methodSignature);

       System.out.println("Exception thrown in AOP Proxy: "+exception);
    }

    @After("execution(* com.java.org.DAO.*.*(..))")
    public void afterFinally(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("-----------------After Finally----------------------");
        System.out.println("Signature: "+methodSignature);
    }
    @AfterReturning(pointcut = "execution(* com.java.org.DAO.*.*(..))",returning = "returnResult")
    public void afterReturning(JoinPoint joinPoint,List<Account> returnResult){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("-----------------AfterReturning----------------------");
        System.out.println("Signature: "+methodSignature);
    }


}
