package com.java.app.aspect;

import com.java.app.dao.Teacher;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

@Aspect
@Component
public class Aspect1 {
    private Logger logger = Logger.getLogger(Aspect1.class.getName());
    @Pointcut("execution(* com.java.app.dao.*.find*(..))")
    private void pointCut2() { }

    @Before("pointCut2()")
    public void BeforeAdvice(){
        logger.warning("\n-------------Before execution-------------");
    }
    @After("pointCut2()")
    public void AfterAdvice(){
        logger.warning("\n-------------After finally execution---------------");
    }

    @AfterReturning(pointcut = "pointCut2()",returning = "list")
    public void AfterReturn(JoinPoint joinPoint,List<Teacher> list){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.warning("\n-------------After Returning execution---------------");
        logger.info("A method with signature "+methodSignature+" was trying to return a list: ");

        for(Teacher teacher:list){
            logger.info(teacher.toString());
            teacher.setFirstName(teacher.getFirstName().toUpperCase());
            teacher.setLastName(teacher.getLastName().toUpperCase());
        }
    }

    @AfterThrowing(pointcut = "pointCut2()",throwing = "exc")
    public void AfterThrowing(JoinPoint joinPoint, Throwable exc){
        logger.warning("\n-------------After Throwing execution---------------");
        logger.warning("!!!!!!!!!!!!!!!!!An exception was thrown in method: " + joinPoint.getSignature().toShortString());
        logger.warning("Exception is: "+exc.getMessage());
    }

    @Around("pointCut2()")
    public List<Teacher> AroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("\n-------------Around execution---------------");
        logger.info("Method: "+joinPoint.getSignature().toShortString()+" will executed in Advice @Around");
        List<Teacher> teacherList = null;

        try {
            teacherList = (List<Teacher>) joinPoint.proceed();
            return teacherList;
        } catch (Throwable e) {
            logger.warning("================================An exception was thrown in Proxy: "+e.getMessage());
            throw e;
        }

    }

}
