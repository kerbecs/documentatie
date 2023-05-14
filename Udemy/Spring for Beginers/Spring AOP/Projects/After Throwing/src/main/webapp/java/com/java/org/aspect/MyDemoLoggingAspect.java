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
       System.out.println("Signature: "+methodSignature);

       System.out.println("Exception thrown in AOP Proxy: "+exception);
    }


}
