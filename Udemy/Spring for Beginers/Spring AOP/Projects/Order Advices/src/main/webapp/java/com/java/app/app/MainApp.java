package com.java.app.app;

import com.java.app.dao.Teacher;
import com.java.app.dao.TeacherDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ContextApp.class);

        TeacherDAO teacherDAO = context.getBean("teacherDAO",TeacherDAO.class);


        List<Teacher> teacherList = teacherDAO.findVipTeachers();

        System.out.println("\n\nTeachers list in MainApp after AfterReturning: ");
        for(Teacher teacher:teacherList)
            System.out.println(teacher);
    }
}
