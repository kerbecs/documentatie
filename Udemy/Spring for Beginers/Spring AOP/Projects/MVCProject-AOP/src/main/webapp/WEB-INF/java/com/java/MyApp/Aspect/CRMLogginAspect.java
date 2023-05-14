package com.java.MyApp.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
@Aspect
public class CRMLogginAspect {
    private Logger logger = Logger.getLogger(CRMLogginAspect.class.getName());

    @Pointcut("execution(public * com.java.MyApp.Controller.*.*(..))")
    private void forControllerPackage() { }

    @Pointcut("execution(public * com.java.MyApp.Service.*.*(..))")
    private void forServicePackage() { }

    @Pointcut("execution(public * com.java.MyApp.dao.*.*(..))")
    private void forDAOPackage() { }

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow() { }

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====> in @Before: calling method: "+method);

        Object[] args = joinPoint.getArgs();

        for(Object obj : args){
            logger.info("====? argument: "+obj.toString());
        }
    }

    @AfterReturning(pointcut = "forAppFlow()",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====> in @AfterReturning: from method: "+method);
        logger.info("====>>result: "+result);
    }

}
