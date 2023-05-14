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

    @Pointcut("execution(* com.java.org.DAO.AccountDAO.findAccounts(..))")
    public void accounts() { }

    @AfterReturning(pointcut = "accounts()", returning = "list")
    public void afterReturningMethod(JoinPoint joinPoint,List<Account> list){

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\nExecuting @AfterReturning on "+method);

        System.out.println("Result before being modified: "+list);

        convertAccountNamesToUpperCase(list);

        System.out.println("List before being modified: "+list);
    }

    private void convertAccountNamesToUpperCase(List<Account> list) {
     for(Account account : list){
         String upperName = account.getName().toUpperCase();
         account.setName(upperName);
     }
    }

}
