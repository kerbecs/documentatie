package com.test.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.lang.instrument.Instrumentation;
import java.util.List;

public class Hello {
    public static void main(String[] args){

        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Teacher.class).addAnnotatedClass(Course.class).buildSessionFactory();
        Session session = null;

        try{
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();
           Teacher teacher = new Teacher("Test","Test",20);
           teacher.addCourse(new Course("Test","25"));
           teacher.addCourse(new Course("Test","40"));
           session.save(teacher);
            session.getTransaction().commit();
            session.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {

        }
    }

}
