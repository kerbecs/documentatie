package com.java.org.aspect;

import com.java.org.aopapp.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect extends AopEpressions{


    @Before("pointCut()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){
        System.out.println("\n=========>>>> Executing before adding an AccountAdvice");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Signature: "+signature);

        Object[] arguments = joinPoint.getArgs();

        for(Object obj : arguments){
            System.out.println(obj);

            if(obj instanceof Account){
                Account account = (Account) obj;

                System.out.println("Accountant: "+account.getName()+" "+account.getLevel());
            }
        }
    }



}
