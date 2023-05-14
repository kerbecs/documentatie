package com.java.org.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

public class AopEpressions {
    @Pointcut("execution(* com.java.org.DAO.*.*(..))")
    public void myPointCut(){ }

    @Pointcut("execution(* com.java.org.DAO.*.set*(..))")
    public void setter() { }

    @Pointcut("execution(* com.java.org.DAO.*.get*(..))")
    public void getter() { }

    @Pointcut("myPointCut() && !(setter() || getter())")
    public void pointCut() { }
}
