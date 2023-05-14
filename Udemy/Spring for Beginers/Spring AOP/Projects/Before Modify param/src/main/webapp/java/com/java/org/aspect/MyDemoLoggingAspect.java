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


    @Before("execution(* com.java.org.DAO.*.setAccountList(..))")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){
        System.out.println("\n=========>>>> Executing before adding an AccountAdvice");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Signature: "+signature);

        Object[] arguments = joinPoint.getArgs();
        List<Account> list = (List<Account>) arguments[0];

        for(Object obj : list){
                Account account = (Account) obj;

                System.out.println("Accountant: "+account.getName()+" "+account.getLevel());
                account.setName(account.getName().toUpperCase());
        }
    }


}
