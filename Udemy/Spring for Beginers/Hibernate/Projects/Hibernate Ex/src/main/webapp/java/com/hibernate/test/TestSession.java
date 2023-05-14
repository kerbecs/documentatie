package com.hibernate.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestSession {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        Session session = factory.getCurrentSession();

        try{
       System.out.println("Creating a new student object");
            session.beginTransaction();
            Student student = session.get(Student.class,1);
            System.out.println(student);
       session.getTransaction().commit();
       System.out.println("Student registered");
        }
        finally {
            factory.close();
        }
    }
}
