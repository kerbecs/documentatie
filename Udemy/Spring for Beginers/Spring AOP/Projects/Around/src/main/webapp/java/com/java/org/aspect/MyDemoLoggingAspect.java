package com.java.org.aspect;

import com.java.org.aopapp.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect extends AopEpressions{

    @Around("execution(* com.java.org.service.*.*(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint)  {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        System.out.println("Signature: "+methodSignature);

        long begin = System.currentTimeMillis();


        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            result = "An exception has thrown, but handled in Proxy";
        }

        long end = System.currentTimeMillis();

        System.out.println("Duration: "+((end-begin)/1000.0)+" seconds");

        return result;
    }
}
